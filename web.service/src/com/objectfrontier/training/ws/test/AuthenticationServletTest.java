/**
 *
 */
package com.objectfrontier.training.ws.test;

import org.apache.http.HttpResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.objectfrontier.training.ws.servlet.AuthenticationServlet;
import com.objectfrontier.training.ws.util.HttpMethod;
import com.objectfrontier.training.ws.util.RequestHelper;

/**
 * @author mohammed.mohammed
 * @since  Nov 22, 2018
 */
public class AuthenticationServletTest {

    AuthenticationServlet authServlet;
    RequestHelper helper;

    @BeforeClass
    private void setup() {
        authServlet = new AuthenticationServlet();
        helper = new RequestHelper();
    }

    @Test (dataProvider = "testDoPostDP")
    private void testDoPost(String uri, String expectedMessage) {
        RequestHelper.setBaseUrl("http://localhost:8080/ws/");
        try {
//            String message = helper.setMethod(HttpMethod.POST)
//                                   .requestString(uri);
            HttpResponse response = helper.setMethod(HttpMethod.GET)
                                          .requestRaw(uri);
            helper.setSecureDetails(response);
//            System.out.println(message);
//            Assert.assertEquals(message, expectedMessage);
        } catch (Exception e) {
//            Assert.fail("Login failed", e);
            e.printStackTrace();
        }
    }

    @DataProvider
    private Object[][] testDoPostDP() {
        String expectedMessage = new StringBuilder("Logged in successfully\n").append("ADMIN Almas").toString();
        return new Object[][] {
                               new Object[] {"login?username=almas@gmail.com&password=admin", expectedMessage}
                              };
    }

    @Test (dataProvider = "testDoDeleteDP")
    private void testDoDelete(String uri, String expectedMessage) {
        RequestHelper.setBaseUrl("http://localhost:8080/ws/");
        try {

            String message = helper.setSecured(true)
                                   .setMethod(HttpMethod.DELETE)
                                   .requestString(uri);
            System.out.println(message);
            Assert.assertEquals(message, expectedMessage);
        } catch (Exception e) {
            Assert.fail("Logout failed", e);
        }
    }

    @DataProvider
    private Object[][] testDoDeleteDP() {
        String expectedMessage = new StringBuilder("Logged out successfully\n").append("Thank you for visiting").toString();
        return new Object[][] {
                               new Object[] {"logout", expectedMessage}
                              };
    }
}
