package com.example.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.Map;

@WebFilter("/*")
public class RequestWrappingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 何もしない
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        // Jerseyで処理された後はリクエストボディが空になってしまうため、
        // 先に取り出してHttpServletRequestのWrapperを作成しておく
        Map<String, String[]> parameterMap = httpServletRequest.getParameterMap();
        HttpServletRequestWrapper requestWrapper = new HttpServletRequestWrapper(httpServletRequest) {
            @Override
            public Map<String, String[]> getParameterMap() {
                return parameterMap;
            }

            @Override
            public String getParameter(String name) {
                String[] params = parameterMap.get(name);
                return params == null || params.length == 0 ? null : params[0];
            }

            @Override
            public String[] getParameterValues(String name) {
                return parameterMap.get(name);
            }
        };
        // Wrapperをフィルタチェーンに渡す
        chain.doFilter(requestWrapper, response);
    }

    @Override
    public void destroy() {
        // 何もしない
    }
}
