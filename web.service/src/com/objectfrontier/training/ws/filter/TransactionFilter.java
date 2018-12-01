/**
 *
 */
package com.objectfrontier.training.ws.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.objectfrontier.training.ws.connection.ConnectionSetup;
import com.objectfrontier.training.ws.exception.AppException;

/**
 * @author mohammed.mohammed
 * @since  Nov 23, 2018
 */
public class TransactionFilter implements Filter {

    @Override
    public void init(FilterConfig config) {}

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws ServletException, IOException {

        ConnectionSetup.setConnection();

        try {
            chain.doFilter(request, response);
            ConnectionSetup.releaseConnection(true);

        } catch (Exception excp) {
            ConnectionSetup.releaseConnection(false);

            if(excp instanceof AppException) {
                AppException appExcp = (AppException) excp;
                throw new AppException(appExcp.getExceptionList(), excp);
            }
            throw (excp);
        }

    }

    @Override
    public void destroy() {}
}
