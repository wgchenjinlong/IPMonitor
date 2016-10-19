package com.example.monitor.filters;

import com.example.monitor.domain.models.User;
import org.springframework.util.StringUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by chenjinlong on 16/10/10.
 */
@WebFilter(filterName = "AuthenticationFilter", urlPatterns = "/*",
        initParams = {@WebInitParam(name = "EXCLUDED_PAGES", value = "/login;/js;/css;/image;/logout")})
public class AuthenticationFilter implements Filter {
    private String excludedPages;
    private String[] excludedPageArray;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        excludedPages = filterConfig.getInitParameter("EXCLUDED_PAGES");
        if (null != excludedPages && excludedPages.length() != 0) {

            excludedPageArray = excludedPages.split(String.valueOf(';'));
        }
        System.out.println("filter init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession();
        boolean isExcludedPage = isExcludePage(httpServletRequest);
        if (isExcludedPage) {
            if (httpServletRequest.getServletPath().equals("/login")
                    || httpServletRequest.getServletPath().equals("/logout")) {

                session.invalidate();
            }
            chain.doFilter(request, response);
        } else {
            User currentUser = (User) session.getAttribute("currentUser");
            if (null != session && currentUser != null) {
                chain.doFilter(request, response);
            } else {

                httpServletResponse.sendRedirect("/login");
            }
        }
    }

    @Override
    public void destroy() {

        this.excludedPages = null;
        this.excludedPageArray = null;
        System.out.println("filter destroy");
    }

    private boolean isExcludePage(HttpServletRequest request) {
        boolean isExcludedPage = false;
        String urlStr = request.getServletPath();
        String[] strings = urlStr.split("/");

        for (String page : excludedPageArray) {
            // 遍历例外url数组
            System.out.println("excludedPage : " + page);
            if (strings.length > 1) {
                if (strings[1].equals(page.substring(1))) {
                    System.out.println(page + ", you're excluded.");
                    isExcludedPage = true;
                    break;
                }
            }

        }
        return isExcludedPage;
    }
}
