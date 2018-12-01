/**
 *
 */
package com.objectfrontier.training.ws.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.objectfrontier.training.ws.pojo.Person;
import com.objectfrontier.training.ws.service.AuthenticationService;

/**
 * @author mohammed.mohammed
 * @since  Nov 20, 2018
 */
public class AuthenticationServlet extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json");
        AuthenticationService auth = new AuthenticationService();
        PrintWriter out = response.getWriter();

        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        String logoutValue = request.getParameter("logout");
        boolean logout = Boolean.parseBoolean(logoutValue);

        if(logout) {
            HttpSession currentSession = request.getSession();

            currentSession.invalidate();
            out.write("Logged out successfully\n");
            out.write("Thank you for visiting");

        } else {

        Person user = auth.authenticateUser(userName, password);

        HttpSession session = request.getSession();
        String sessionId = session.getId();
        session.setAttribute("sessionId", sessionId);
        session.setAttribute("userName", userName);
        session.setAttribute("isAdmin", user.isAdmin());

        Cookie cookie = new Cookie("JSESSIONID", sessionId);
        response.addCookie(cookie);

        out.write("Logged in successfully\n");
        out.write(sessionId);

        }
        out.close();
    }

    @Override
    public void doDelete(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        session.invalidate();
        out.write("Logged out successfully\n");
        out.write("Thank you for visiting");
    }

}