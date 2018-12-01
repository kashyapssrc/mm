/**
 *
 */
package com.objectfrontier.training.ws.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

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
 * @since  Nov 19, 2018
 */
public class AuthenticationFilter implements Filter{



    @Override
    public void init(FilterConfig config) {}

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws ServletException, IOException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(false);
        Object userName = session.getAttribute("userName");
        PrintWriter out = response.getWriter();

        if(Objects.isNull(userName)) {
            throw new AppException(AppErrorCode.ERROR_NOT_LOGGED_IN);
        } else {
            chain.doFilter(request, response);
        }

        out.close();
    }

    @Override
    public void destroy() {}

}
