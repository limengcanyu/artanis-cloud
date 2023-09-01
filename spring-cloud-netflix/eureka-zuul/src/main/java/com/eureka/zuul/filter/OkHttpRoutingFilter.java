package com.eureka.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import okhttp3.*;
import okhttp3.internal.http.HttpMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.ProxyRequestHelper;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.StreamUtils;

import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.ROUTE_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SIMPLE_HOST_ROUTING_FILTER_ORDER;

/**
 * <p>Description: Route Filter</p>
 *
 * @author Rock Jiang
 * @version 1.0
 * @date 2019/3/19 0019
 */
@Component
public class OkHttpRoutingFilter extends ZuulFilter {
    private static final Logger logger = LoggerFactory.getLogger(OkHttpRoutingFilter.class);

    @Autowired
    private ProxyRequestHelper helper;

    @Override
    public String filterType() {
        logger.debug("route filter === filter type");

        return ROUTE_TYPE;
    }

    @Override
    public int filterOrder() {
        logger.debug("route filter === filter order");

        return SIMPLE_HOST_ROUTING_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        logger.debug("route filter === should filter");

//        return RequestContext.getCurrentContext().getRouteHost() != null
//                && RequestContext.getCurrentContext().sendZuulResponse();

        return true;
    }

    @Override
    public Object run() {
        logger.debug("route filter === run");

        try {
            OkHttpClient httpClient = new OkHttpClient.Builder()
                    // customize
                    .build();

            RequestContext context = RequestContext.getCurrentContext();
            HttpServletRequest request = context.getRequest();

            // 获取请求方法
            String method = request.getMethod();
            logger.debug("request method: " + method);

            String requestURI = request.getRequestURI();
            logger.debug("request URI: " + requestURI);

            String uri = this.helper.buildZuulRequestURI(request);
            logger.debug("Zuul request URI: " + uri);

            // 请求Headers处理
            Headers.Builder headers = new Headers.Builder();
            Enumeration<String> headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                Enumeration<String> values = request.getHeaders(name);

                while (values.hasMoreElements()) {
                    String value = values.nextElement();
                    headers.add(name, value);
                }
            }

            InputStream inputStream = request.getInputStream();

            RequestBody requestBody = null;
            if (inputStream != null && HttpMethod.permitsRequestBody(method)) {
                MediaType mediaType = null;
                if (headers.get("Content-Type") != null) {
                    mediaType = MediaType.parse(headers.get("Content-Type"));
                }
                requestBody = RequestBody.create(mediaType, StreamUtils.copyToByteArray(inputStream));
            }

            Request.Builder builder = new Request.Builder()
                    .headers(headers.build())
                    .url(uri)
                    .method(method, requestBody);

            Response response = httpClient.newCall(builder.build()).execute();

            LinkedMultiValueMap<String, String> responseHeaders = new LinkedMultiValueMap<>();

            for (Map.Entry<String, List<String>> entry : response.headers().toMultimap().entrySet()) {
                responseHeaders.put(entry.getKey(), entry.getValue());
            }

            this.helper.setResponse(response.code(), response.body().byteStream(),
                    responseHeaders);
            context.setRouteHost(null); // prevent SimpleHostRoutingFilter from running
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
