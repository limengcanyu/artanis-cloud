package com.spring.cloud.common.filter;

import com.alibaba.fastjson.JSON;
import com.spring.cloud.common.constant.TokenConst;
import com.spring.cloud.common.entity.User;
import com.spring.cloud.common.util.ServletUtil;
import com.spring.cloud.common.util.ThreadContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class SecurityFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest httpServletRequest) {
            String tenantId = httpServletRequest.getHeader(TokenConst.TENANT_ID);
            String companyId = httpServletRequest.getHeader(TokenConst.COMPANY_ID);
            String userId = httpServletRequest.getHeader(TokenConst.USER_ID);
            String verifySignature = httpServletRequest.getHeader(TokenConst.VERIFY_SIGNATURE);
            log.debug("tenantId: {} companyId: {} userId: {} verifySignature: {}", tenantId, companyId, userId, verifySignature);

            if (TokenConst.VERIFY_SIGNATURE_FAILED.equals(verifySignature)) {
                Map<String, String> error = new HashMap<>();
                error.put("code", "1");
                error.put("message", "非法签名！");
                ServletUtil.writeResponse(response, JSON.toJSONString(error));
                return;
            }

            ThreadContextUtil.setUser(User.builder().tenantId(tenantId).companyId(companyId).userId(userId).build());
        }

        chain.doFilter(request, response);
    }
}
