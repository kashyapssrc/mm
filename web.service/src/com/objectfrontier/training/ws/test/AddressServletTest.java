/**
 *
 */
package com.objectfrontier.training.ws.test;

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
import com.objectfrontier.training.ws.servlet.TestBaseServlet;
import com.objectfrontier.training.ws.util.CsvDataReader;
import com.objectfrontier.training.ws.util.HttpMethod;
import com.objectfrontier.training.ws.util.JsonUtil;
import com.objectfrontier.training.ws.util.RequestHelper;

/**
 * @author mohammed.mohammed
 * @since  Nov 12, 2018
 */
public class AddressServletTest extends TestBaseServlet {

    private static RequestHelper helper;

    @BeforeClass
    private void setUp() {
        helper = new RequestHelper();
        RequestHelper.setBaseUrl("http://localhost:8080/ws/");
//        HttpResponse response;
//        try {
//            response = helper.setSecured(true)
//                             .setMethod(HttpMethod.GET)
//                             .requestRaw("login?username=almas@gmail.com&password=admin");
//            System.out.println(response);
//            helper.setSecureDetails(response);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        String uri = "login?username=almas@gmail.com&password=admin";
        helper = login(uri);
    }

    @Test(priority = 1)
    public void testDoPut() throws Exception {

        RequestHelper.setBaseUrl("http://localhost:8080/ws/");
        String uri = "address";
        List<Address> addressDataList = CsvDataReader.readAddressCsvFile();
        for(Address addressData : addressDataList) {
            Address actualCreatedAddress = helper.setSecured(true)
                                                 .setMethod(HttpMethod.PUT)
                                                 .setInput(addressData)
                                                 .requestObject(uri, Address.class);

            addressData.setId(actualCreatedAddress.getId());
            Assert.assertEquals(JsonUtil.toJson(actualCreatedAddress), JsonUtil.toJson(addressData));
        }
    }

    @Test(priority = 2, dataProvider = "testDoGet_positiveDP")
    private void testDoGet_positive(String url, Object expectedResult) throws Exception {

        RequestHelper.setBaseUrl("http://localhost:8080/ws/");
        String addressDetails = helper.setSecured(true)
                                      .setMethod(HttpMethod.GET)
                                      .requestString(url);

        Assert.assertEquals(addressDetails, JsonUtil.toJson(expectedResult));
    }

    @DataProvider
    private Object[][] testDoGet_positiveDP() {

        Address addressOne = new Address("Anupratap Colony", "Rawatbhata", 323307);
        addressOne.setId(1l);

        Address addressTwo = new Address("Vaishali", "Delhi", 201204);
        addressTwo.setId(2l);

        Address addressThree = new Address("Sector 16", "Greater Noida", 220504);
        addressThree.setId(3l);

        Address addressFour = new Address("Rajiv Gandhi Nagar", "Kota", 323506);
        addressFour.setId(4l);

        Address addressFive = new Address("Taramani", "Chennai", 205698);
        addressFive.setId(5l);

        Address addressSix = new Address("Pallavaram", "Chennai",205532);
        addressSix.setId(6l);

        Address addressSeven = new Address("Hell's Kitchen", "New York", 442244);
        addressSeven.setId(7l);

        List<Address> addressList = new ArrayList<>();
        addressList.add(addressOne);
        addressList.add(addressTwo);
        addressList.add(addressThree);
        addressList.add(addressFour);
        addressList.add(addressFive);
        addressList.add(addressSix);
        addressList.add(addressSeven);

        Address searchResultOne = new Address("Anupratap Colony", "Rawatbhata", 0);
        searchResultOne.setId(1l);
        Address searchResultTwo = new Address("Rajiv Gandhi Nagar", "Kota", 0);
        searchResultTwo.setId(4l);

        List<Address> searchResultList = new ArrayList<>();
        searchResultList.add(searchResultOne);
        searchResultList.add(searchResultTwo);

        return new Object[][] {
                               new Object[]{"address", addressList},
                               new Object[]{"address?id=4", addressFour},
                               new Object[]{"address?search=true&searchField=city&searchKeyword=ta&resultSetField=street,id,city", searchResultList}
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
                               new Object[] {"address?id=abc", expectedErrorOne},
                               new Object[] {"address?id=10", expectedErrorTwo}
                              };
    }


    @Test(priority = 4, dataProvider = "testDoPostDP")
    public void testDoPost(String uri, Address inputAddress) throws Exception {

        RequestHelper.setBaseUrl("http://localhost:8080/ws/");
        Address actualUpdatedAddress = helper.setSecured(true)
                                             .setMethod(HttpMethod.POST)
                                             .setInput(inputAddress)
                                             .requestObject(uri, Address.class);

        Assert.assertEquals(JsonUtil.toJson(actualUpdatedAddress), JsonUtil.toJson(inputAddress));
    }

    @DataProvider
    private Object[][] testDoPostDP() {
        Address addressOne = new Address("Harlem", "New York", 442244);
        addressOne.setId(7l);

        return new Object[][] {
                               new Object[]{"address", addressOne},
                              };
    }

    @Test(priority = 5, dataProvider = "testDoDeleteDP")
    private void testDoDelete(String uri, Address expectedDeletedAddress) throws Exception {

        RequestHelper.setBaseUrl("http://localhost:8080/ws/");
        String deletedAddressDetails = helper.setSecured(true)
                                             .setMethod(HttpMethod.DELETE)
                                             .requestString(uri);

        Assert.assertEquals(deletedAddressDetails, JsonUtil.toJson(expectedDeletedAddress));
    }

    @DataProvider
    private Object[][] testDoDeleteDP() {
        Address addressOne = new Address("Harlem", "New York", 442244);
        addressOne.setId(7l);

        return new Object[][] {
                               new Object[]{"address?id=7", addressOne},
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
