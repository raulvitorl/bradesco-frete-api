package br.com.bradesco.frete.api.helpers;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.util.ContentCachingRequestWrapper;

public class LoggingFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request);
        try {
            chain.doFilter(wrappedRequest, res);
        } finally {
            logRequestBody(wrappedRequest);
        }
    }

    private static void logRequestBody(ContentCachingRequestWrapper request) {

        byte[] buf = request.getContentAsByteArray();
        if (buf.length > 0) {
            try {
                String requestBody = new String(buf, 0, buf.length, request.getCharacterEncoding());
                System.out.println(requestBody);
            } catch (Exception e) {
            }
        }
    }
}
