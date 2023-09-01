package com.spring.cloud.common.interceptor;

import com.spring.cloud.common.constant.TokenConst;
import com.spring.cloud.common.entity.User;
import com.spring.cloud.common.util.ThreadContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;

@Slf4j
@Component
public class SecurityInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String tenantId = request.getHeader(TokenConst.TENANT_ID);
        String companyId = request.getHeader(TokenConst.COMPANY_ID);
        String userId = request.getHeader(TokenConst.USER_ID);
        String verifySignature = request.getHeader(TokenConst.VERIFY_SIGNATURE);
        log.debug("tenantId: {} companyId: {} userId: {} verifySignature: {}", tenantId, companyId, userId, verifySignature);

        ThreadContextUtil.setUser(User.builder().tenantId(tenantId).companyId(companyId).userId(userId).build());

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
