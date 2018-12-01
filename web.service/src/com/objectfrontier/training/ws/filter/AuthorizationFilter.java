/**
 *
 */
package com.objectfrontier.training.ws.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.objectfrontier.training.ws.exception.AppErrorCode;
import com.objectfrontier.training.ws.exception.AppException;

/**
 * @author mohammed.mohammed
 * @since  Nov 23, 2018
 */
public class AuthorizationFilter implements Filter {

    @Override
    public void init(FilterConfig config) {}

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        PrintWriter out = response.getWriter();
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(false);

        if (session.getAttribute("isAdmin").equals(true)) {
        chain.doFilter(request, response);

        } else {
            if (session.getAttribute("isAdmin").equals(false) &&
                req.getMethod().equalsIgnoreCase("GET") ||
                req.getMethod().equalsIgnoreCase("POST")) {

                chain.doFilter(request, response);
            } else {
                throw new AppException(AppErrorCode.ERROR_ACCESS_DENIED);
            }
        }
    }

    @Override
    public void destroy() {}
}
