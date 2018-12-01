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
import javax.servlet.http.HttpServletResponse;

import com.objectfrontier.training.ws.exception.AppException;

/**
 * @author mohammed.mohammed
 * @since  Nov 23, 2018
 */
public class ErrorFilter implements Filter{

    FilterConfig config;

    @Override
    public void init(FilterConfig config) {
        this.config = config;
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        HttpServletResponse res = (HttpServletResponse) response;
        String construction = config.getInitParameter("construction");

        if (construction.equals("yes")) {
            out.write("This service is under construction");

        } else {
            try {
                chain.doFilter(request, response);

            } catch (Exception excp) {
                if(excp instanceof AppException) {
                    AppException appExcp = (AppException) excp;
                    out.write(appExcp.toString());
                } else {
                    res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    out.write("INTERNAL SERVER ERROR");
                }
            }
        }

        out.close();
    }

    @Override
    public void destroy() {}
}
