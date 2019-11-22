package co.id.gooddoctor.gundala.advice;

import co.id.gooddoctor.gundala.infrastructure.model.BaseResponse;
import co.id.gooddoctor.gundala.infrastructure.security.HMACHelper;
import co.id.gooddoctor.gundala.infrastructure.util.JsonUtil;
import org.springframework.http.MediaType;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//@Component
//@Order(1)
public class SecurityAdvice implements Filter {

    private List<String> excludedUrls;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        String url = httpServletRequest.getServletPath();

        boolean staticResources = (url.contains("/swagger-ui.html") || url.contains("/v2/api-docs"));
        boolean allowed = staticResources ? true : false;

        HMACHelper hmacHelper = new HMACHelper();
        boolean calculateHMACSignature = hmacHelper.calculateHMACSignature(
                httpServletRequest.getHeader("time"), httpServletRequest.getHeader("hmac"));

        if (!calculateHMACSignature && !allowed) {
            BaseResponse baseResponse = new BaseResponse().failedProcess("HMAC Signature not match");
            String responseJson = JsonUtil.objectToJson(baseResponse);

            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            httpServletResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            httpServletResponse.getWriter().write(responseJson);

            httpServletResponse.getWriter().flush();
            httpServletResponse.getWriter().close();

        }
    }

}
