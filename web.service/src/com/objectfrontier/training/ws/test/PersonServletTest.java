/**
 *
 */
package com.objectfrontier.training.ws.test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.objectfrontier.training.ws.exception.AppErrorCode;
import com.objectfrontier.training.ws.exception.AppException;
import com.objectfrontier.training.ws.pojo.Address;
import com.objectfrontier.training.ws.pojo.Person;
import com.objectfrontier.training.ws.servlet.TestBaseServlet;
import com.objectfrontier.training.ws.util.CsvDataReader;
import com.objectfrontier.training.ws.util.HttpMethod;
import com.objectfrontier.training.ws.util.JsonUtil;
import com.objectfrontier.training.ws.util.RequestHelper;

/**
 * @author mohammed.mohammed
 * @since  Nov 13, 2018
 */
public class PersonServletTest extends TestBaseServlet {

    private static RequestHelper helper;

    @BeforeClass
    private void setUp() throws Exception {
        helper = new RequestHelper();
        RequestHelper.setBaseUrl("http://localhost:8080/ws/");
//        HttpResponse response;
//        try {
//            response = helper.setSecured(true)
//                             .setMethod(HttpMethod.GET)
                             String uri = "login?username=almas@gmail.com&password=admin";
//                            requestRaw(uri);
//            helper.setSecureDetails(response);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        helper = login(uri);

    }

    @Test(priority = 1)
    public void testDoPut() throws Exception {

        RequestHelper.setBaseUrl("http://localhost:8080/ws/");
        String uri = "person";
        List<Person> personDataList = CsvDataReader.readPersonCsvFile();
        for(Person personData : personDataList) {
            Person actualCreatedPerson = helper.setSecured(true)
                                               .setMethod(HttpMethod.PUT)
                                               .setInput(personData)
                                               .requestObject(uri, Person.class);

            personData.setId(actualCreatedPerson.getId());
            personData.getAddress().setId(actualCreatedPerson.getAddress().getId());

            Assert.assertEquals(JsonUtil.toJson(actualCreatedPerson), JsonUtil.toJson(personData));
        }
    }

    @Test(priority = 2, dataProvider = "testDoGet_positiveDP")
    private void testDoGet_positive(String uri, Object expectedResult) throws Exception {

        RequestHelper.setBaseUrl("http://localhost:8080/ws/");
        String personDetails = helper.setSecured(true)
                                     .setMethod(HttpMethod.GET)
                                     .requestString(uri);

        Assert.assertEquals(personDetails, JsonUtil.toJson(expectedResult));
    }

    @DataProvider
    private Object[][] testDoGet_positiveDP() {

        Person personOne = new Person("Almas", "Ansari", "almas@gmail.com", Date.valueOf("1997-06-02"));
        personOne.setAddress(new Address("Anupratap Colony", "Rawatbhata",323307));
        personOne.setId(1l);
        personOne.getAddress().setId(1l);
        personOne.setAdmin(true);

        Person personTwo = new Person("Mayank", "Verma", "mayank@gmail.com", Date.valueOf("1996-5-26"));
        personTwo.setAddress(new Address("Vaishali", "Delhi", 201204));
        personTwo.setId(2l);
        personTwo.getAddress().setId(2l);
        personTwo.setAdmin(false);


        Person personThree = new Person("Raunak", "Chaudhary", "raunak@gmail.com", Date.valueOf("1996-10-30"));
        personThree.setAddress(new Address("Sector 16", "Greater Noida", 220504));
        personThree.setId(3l);
        personThree.getAddress().setId(3l);
        personThree.setAdmin(false);

        Person personFour = new Person("Rehaan", "Khan", "rehaan@gmail.com", Date.valueOf("1995-12-8"));
        personFour.setAddress(new Address("Rajiv Gandhi Nagar", "Kota", 323506));
        personFour.setId(4l);
        personFour.getAddress().setId(4l);
        personFour.setAdmin(false);

        Person personFive = new Person("Naveen", "Raj", "naveen@gmail.com", Date.valueOf("1996-07-15"));
        personFive.setAddress(new Address("Taramani", "Chennai", 205698));
        personFive.setId(5l);
        personFive.getAddress().setId(5l);
        personFive.setAdmin(false);

        Person personSix = new Person("Hariharan", "Sekar", "hariharan@gmail.com", Date.valueOf("1996-06-26"));
        personSix.setAddress(new Address("Pallavaram", "Chennai",205532));
        personSix.setId(6l);
        personSix.getAddress().setId(6l);
        personSix.setAdmin(false);

        Person personSeven = new Person("Karthike", "Kandasamy", "kk@gmail.com", Date.valueOf("1996-06-20"));
        personSeven.setAddress(new Address("Pillaiyar Koil", "Chennai", 600103));
        personSeven.setId(7l);
        personSeven.getAddress().setId(7l);
        personSeven.setAdmin(false);

        List<Person> personList = new ArrayList<>();
        personList.add(personOne);
        personList.add(personTwo);
        personList.add(personThree);
        personList.add(personFour);
        personList.add(personFive);
        personList.add(personSix);
//        personList.add(personSeven);

        Person searchResultOne = new Person("Almas", "Ansari", "almas@gmail.com", null);
        searchResultOne.setAddress(new Address());
        searchResultOne.setId(1l);

        Person searchResultTwo = new Person("Hariharan", "Sekar", "hariharan@gmail.com", null);
        searchResultTwo.setAddress(new Address());
        searchResultTwo.setId(6l);

        List<Person> searchResultList = new ArrayList<>();
        searchResultList.add(searchResultOne);
        searchResultList.add(searchResultTwo);

        String readAllPersonURI = "person?includeAddress=true";
        String readPersonURI = "person?includeAddress=true&id=4";
        String searchPersonURI = new StringBuilder("person?search=true").append("&searchField=first%20name,last%20name")
                                                                        .append("&searchKeyword=ri")
                                                                        .append("&resultSetField=first%20name,last%20name,email,id")
                                                                        .toString();

        return new Object[][] {
                               new Object[]{readAllPersonURI, personList},
                               new Object[]{readPersonURI, personFour},
                               new Object[]{searchPersonURI, searchResultList}
                              };
    }

    @Test(priority = 3, dataProvider = "testDoGet_negativeDP")
    private void testDoGet_negative(String uri, Exception expectedError) throws Exception {

        RequestHelper.setBaseUrl("http://localhost:8080/ws/");
        String actualString = helper.setSecured(true)
                                    .setMethod(HttpMethod.GET)
                                    .requestString(uri);

        Assert.assertEquals(actualString, expectedError.toString());
    }

    @DataProvider
    private Object[][] testDoGet_negativeDP() {
        Exception expectedErrorOne = new AppException(AppErrorCode.ERROR_INVALID_DATA_FORMAT);
        Exception expectedErrorTwo = new AppException(AppErrorCode.ERROR_NO_RECORD_FOUND);

        return new Object[][] {
                               new Object[] {"person?id=abc", expectedErrorOne},
                               new Object[] {"person?id=10", expectedErrorTwo}
                              };
    }

    @Test(priority = 4, dataProvider = "testDoPostDP")
    public void testDoPost(String uri, Person inputPerson) throws Exception {

        RequestHelper.setBaseUrl("http://localhost:8080/ws/");
        Person actualUpdatedPerson = helper.setSecured(true)
                                           .setMethod(HttpMethod.POST)
                                           .setInput(inputPerson)
                                           .requestObject(uri, Person.class);

        Assert.assertEquals(JsonUtil.toJson(actualUpdatedPerson), JsonUtil.toJson(inputPerson));
    }

    @DataProvider
    private Object[][] testDoPostDP() {
        Person personOne = new Person("Farhaan", "Akhtar", "farhaan.cool@gmail.com", Date.valueOf("1995-12-08"));
        Address addressOne = new Address("Rajiv Gandhi Nagar", "Kota", 323506);
        personOne.setAddress(addressOne);
        personOne.setId(4l);
        personOne.getAddress().setId(4l);

        return new Object[][] {
                               new Object[]{"person?includeAddress=true", personOne},
                              };
    }

    @Test(priority = 5, dataProvider = "testDoDeleteDP")
    private void testDoDelete(String uri, Person expectedDeletedPerson) throws Exception {

        RequestHelper.setBaseUrl("http://localhost:8080/ws/");
        String deletedPersonDetails = helper.setSecured(true)
                                            .setMethod(HttpMethod.DELETE)
                                            .requestString(uri);

        Assert.assertEquals(deletedPersonDetails, JsonUtil.toJson(expectedDeletedPerson));
    }

    @DataProvider
    private Object[][] testDoDeleteDP() {
        Person personOne = new Person("Karthike", "Kandasamy", "kk@gmail.com", Date.valueOf("1996-06-20"));
        personOne.setAddress(new Address("Pillaiyar Koil", "Chennai", 600103));
        personOne.setId(7l);
        personOne.getAddress().setId(7l);

        return new Object[][] {
                               new Object[]{"person?id=7", personOne},
                              };
    }

    @AfterClass
    private void tearDown() {
        RequestHelper helper = new RequestHelper();
        RequestHelper.setBaseUrl("http://localhost:8080/ws/");

        try {
            String message = helper.setSecured(true)
                                   .setMethod(HttpMethod.DELETE)
                                   .requestString("logout");
            System.out.println(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
