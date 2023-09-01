package com.eureka.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import java.util.Enumeration;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.*;

/**
 * <p>Description: Pre Filter</p>
 *
 * @author Rock Jiang
 * @version 1.0
 * @date 2019/3/19 0019
 */
@Component
public class QueryParamPreFilter extends ZuulFilter {
    private static final Logger logger = LoggerFactory.getLogger(QueryParamPreFilter.class);

    /**
     * 定义filter的类型，有pre、route、post、error四种
     *
     * @return
     */
    @Override
    public String filterType() {
        logger.debug("pre filter === filter type");

        return PRE_TYPE;
    }

    /**
     * 定义filter的顺序，数字越小表示顺序越高，越先执行
     *
     * @return
     */
    @Override
    public int filterOrder() {
        logger.debug("pre filter === filter order");

        return PRE_DECORATION_FILTER_ORDER - 1; // run before PreDecoration
    }

    /**
     * 表示是否需要执行该filter，true表示执行，false表示不执行
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        logger.debug("pre filter === should filter");

//        RequestContext ctx = RequestContext.getCurrentContext();
//        return !ctx.containsKey(FORWARD_TO_KEY) // a filter has already forwarded
//                && !ctx.containsKey(SERVICE_ID_KEY); // a filter has already determined serviceId

        return true;
    }

    /**
     * filter需要执行的具体操作
     *
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        logger.debug("pre filter === run");

        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            String parameterValue = request.getParameter(parameterName);
            System.out.println("parameter Name: " + parameterName + " Value: " + parameterValue);
        }

//        if (request.getParameter("sample") != null) {
//            // put the serviceId in `RequestContext`
//            ctx.put(SERVICE_ID_KEY, request.getParameter("foo"));
//        }

        return null;
    }
}
