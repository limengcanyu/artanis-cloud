package com.eureka.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

import java.util.UUID;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.POST_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SEND_RESPONSE_FILTER_ORDER;

/**
 * <p>Description: Post Filter</p>
 *
 * @author Rock Jiang
 * @version 1.0
 * @date 2019/3/19 0019
 */
@Component
public class AddResponseHeaderFilter extends ZuulFilter {
    private static final Logger logger = LoggerFactory.getLogger(AddResponseHeaderFilter.class);

    @Override
    public String filterType() {
        logger.debug("post filter === filter type");

        return POST_TYPE;
    }

    @Override
    public int filterOrder() {
        logger.debug("post filter === filter order");

        return SEND_RESPONSE_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        logger.debug("post filter === should filter");

        return true;
    }

    @Override
    public Object run() {
        logger.debug("post filter === run");

//        RequestContext context = RequestContext.getCurrentContext();
//        HttpServletResponse servletResponse = context.getResponse();
//        servletResponse.addHeader("X-Sample", UUID.randomUUID().toString());

        return null;
    }
}
