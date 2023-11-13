package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@WebFilter("/*")
@PropertySource({ "classpath:/application.properties" })
public class CORSFilter implements Filter {

    @Value("${cors.domains}")
    private String domains;

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        final HttpServletResponse response = (HttpServletResponse) res;
        final HttpServletRequest request = (HttpServletRequest) req;
        String origin = request.getHeader(HttpHeaders.ORIGIN);

        if(domains != null && origin!= null){
            String d[]= domains.split(",");
            for(String x: d){
                if(x.equalsIgnoreCase(origin)){
                    response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, origin);
                }
            }
        }

        response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "POST, PUT, GET, OPTIONS");
        response.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "Origin, authorization, cache-control, content-type, captcha");
        response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
        response.setHeader(HttpHeaders.CACHE_CONTROL, "no-cache");
        response.setHeader(HttpHeaders.ACCESS_CONTROL_MAX_AGE, "3600");
        if ("OPTIONS".equalsIgnoreCase(((HttpServletRequest) req).getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            chain.doFilter(req, res);
        }
    }

    public void destroy() {
    }

    public void init(FilterConfig config) throws ServletException {
    }
}
