package com.spring.cloud.common.filter;

import cn.hutool.crypto.SignUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.cloud.common.constant.RequestConst;
import com.spring.cloud.common.dto.Result;
import com.spring.cloud.common.entity.User;
import com.spring.cloud.common.util.ServletUtil;
import com.spring.cloud.common.util.ThreadContextUtil;
import com.spring.cloud.common.wrapper.PostBodyReaderRequestWrapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.util.TreeMap;

/**
 * 1. 验证token
 * 2. 验证签名
 * 3. 在上下文中设置用户信息
 * <p>
 * 验证token、签名不放在gateway中，减轻gateway的压力
 */
@Slf4j
//@Component
public class SecurityFilterBackup implements Filter {
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest httpServletRequest) {
            String requestMethod = httpServletRequest.getMethod();
            log.debug("requestMethod: {}", requestMethod);

            if (RequestConst.REQUEST_METHOD_GET.equals(requestMethod)) {
                log.debug("a GET Request come here");
                filterChain.doFilter(servletRequest, servletResponse);
            }

            if (RequestConst.REQUEST_METHOD_POST.equals(requestMethod)) {
                PostBodyReaderRequestWrapper requestWrapper = new PostBodyReaderRequestWrapper(httpServletRequest);

                String token = httpServletRequest.getHeader(RequestConst.TOKEN);
                String sign = httpServletRequest.getHeader(RequestConst.SIGN);
                String verifySign = httpServletRequest.getHeader(RequestConst.VERIFY_SIGN);
                log.debug("token: {} sign: {} verifySign: {}", token, sign, verifySign);

                ThreadContextUtil.setUser(User.builder().userId("U002").username("南风不竞").build());

                if ("true".equals(verifySign)) {
                    String body = requestWrapper.getBody();
                    log.debug("doFilter body: {}", body);

                    if (!ObjectUtils.isEmpty(body)) {
                        TreeMap bodyTreeMap = objectMapper.readValue(body, TreeMap.class);

                        String paramsSign = SignUtil.signParamsMd5(bodyTreeMap);
                        log.debug("paramsSign: {} headerSign: {}", paramsSign, sign);

                        if (!paramsSign.equals(sign)) {
                            Result error = Result.builder().code("1").message("签名验证失败！").build();
                            ServletUtil.writeResponse(servletResponse, objectMapper.writeValueAsString(error));
                            return;
                        }
                    }
                }

                filterChain.doFilter(requestWrapper, servletResponse);
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

}
