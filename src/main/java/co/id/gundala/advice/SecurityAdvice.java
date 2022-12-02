package co.id.gundala.advice;

import co.id.gundala.infrastructure.model.BaseResponse;
import co.id.gundala.infrastructure.security.HMACHelper;
import co.id.gundala.infrastructure.util.JsonUtil;

import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// @Component
// @Order(1)
public class SecurityAdvice implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
            FilterChain filterChain) throws IOException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        String url = httpServletRequest.getServletPath();

        boolean staticResourcesAllowed = url.contains("swagger-ui") || url.contains("/v3/api-docs");

        if (!staticResourcesAllowed) {

            HMACHelper hmacHelper = new HMACHelper();
            boolean calculateHMACSignature = hmacHelper.calculateHMACSignature(
                    httpServletRequest.getHeader("time"), httpServletRequest.getHeader("hmac"));

            if (!calculateHMACSignature) {
                BaseResponse baseResponse = new BaseResponse().failedProcess("HMAC Signature not match");
                String responseJson = JsonUtil.objectToJson(baseResponse);

                httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
                httpServletResponse.getWriter().write(responseJson);

                httpServletResponse.getWriter().flush();
                httpServletResponse.getWriter().close();

            }
        }
    }

}
