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

import com.objectfrontier.training.ws.connection.ConnectionSetup;
import com.objectfrontier.training.ws.exception.AppErrorCode;
import com.objectfrontier.training.ws.exception.AppException;
import com.objectfrontier.training.ws.pojo.Address;
import com.objectfrontier.training.ws.service.AddressService;


/**
 * @author mohammed.mohammed
 * @since  Oct 6, 2018
 */
@Test
public class AddressServiceTest {
    AddressService addressService;

    @BeforeClass
    private void setUp() throws Exception{
        addressService = new AddressService();
    }

    @Test(priority = 1, dataProvider = "testCreateAddress_positiveDP")
    private void testCreateAddress_positive(Address addressDetails, Address expectedAddress) throws Exception {

        ConnectionSetup.setConnection();

        try {
            Address actualAddress = addressService.createAddress(addressDetails);
            Assert.assertEquals(actualAddress.toString(), expectedAddress.toString());
            ConnectionSetup.releaseConnection(true);
        } catch (Exception e) {
            Assert.fail("Unexpected exception for inserting values in address table");
            ConnectionSetup.releaseConnection(false);
        }
    }

    @DataProvider
    private Object[][] testCreateAddress_positiveDP() {
        Address expectedAddressOne = new Address("Anupratap Colony","Rawatbhata",323307);
        expectedAddressOne.setId(1l);
        Address expectedAddressTwo = new Address("Vaishali", "Delhi", 201204);
        expectedAddressTwo.setId(2l);
        Address expectedAddressThree = new Address("Sector 16", "Greater Noida", 220504);
        expectedAddressThree.setId(3l);
        Address expectedAddressFour = new Address("Rajiv Gandhi Nagar", "Kota", 323506);
        expectedAddressFour.setId(4l);

        return new Object[][] {
                                {new Address("Anupratap Colony","Rawatbhata",323307), expectedAddressOne},
                                {new Address("Vaishali", "Delhi", 201204), expectedAddressTwo},
                                {new Address("Sector 16", "Greater Noida", 220504), expectedAddressThree},
                                {new Address("Rajiv Gandhi Nagar", "Kota", 323506), expectedAddressFour}
                              };
    }

    @Test(priority = 2, dataProvider = "testCreateAddress_negativeDP")
    private void testCreateAddress_negative(Address addressDetails, List<AppErrorCode> expectedException) throws Exception {

        ConnectionSetup.setConnection();

        try {
            addressService.createAddress(addressDetails);
            Assert.fail("Expected an exception");
            ConnectionSetup.releaseConnection(true);
        } catch (AppException e) {
            Assert.assertEquals(e.getExceptionList().toString(), expectedException.toString());
            ConnectionSetup.releaseConnection(false);
        }
    }

    @DataProvider
    private Object[][] testCreateAddress_negativeDP() {
//        Address newAddressOne = new Address("Anupratap Colony", "Rawatbhata", 323307);
//        newAddressOne.setId(1);

//        List<AppErrorCode> expectedListOne = new ArrayList<>();
//        expectedListOne.add(AppErrorCode.ERROR_NOT_AUTO_GEN_ADDR_ID);
        List<AppErrorCode> expectedExceptionFirst = new ArrayList<>();
        expectedExceptionFirst.add(AppErrorCode.ERROR_NULL_POSTAL_CODE);

        List<AppErrorCode> expectedExceptionSecond = new ArrayList<>();
        expectedExceptionSecond.add(AppErrorCode.ERROR_NULL_STREET);
        expectedExceptionSecond.add(AppErrorCode.ERROR_NULL_CITY);

        List<AppErrorCode> expectedExceptionThird = new ArrayList<>();
        expectedExceptionThird.add(AppErrorCode.ERROR_NULL_STREET);
        expectedExceptionThird.add(AppErrorCode.ERROR_NULL_CITY);
        expectedExceptionThird.add(AppErrorCode.ERROR_NULL_POSTAL_CODE);

        return new Object[][] {
//                                  new Object[] {newAddressOne, expectedListOne}
                                new Object[] {new Address("Anupratap Colony","Rawatbhata", 0), expectedExceptionFirst},
                                new Object[] {new Address("", "", 201204), expectedExceptionSecond},
                                new Object[] {new Address("", "", 0), expectedExceptionThird}
                              };
    }

    @Test(priority = 3, dataProvider = "testSearchAddress_positiveDP")
    private void testSearchAddress_positive(String[] searchField,
                                            String searchKeyword,
                                            List<Address> expectedResult,
                                            String... resultSetColumn) throws Exception {

        ConnectionSetup.setConnection();

        try {
            List<Address> actualResult = addressService.searchAddress(searchField, searchKeyword, resultSetColumn);
            Assert.assertEquals(actualResult.toString(), expectedResult.toString());
            ConnectionSetup.releaseConnection(true);
        } catch (Exception e) {
            Assert.fail("Unexpected exception occurred for searching a record");
            ConnectionSetup.releaseConnection(false);
        }
    }

    @DataProvider
    private Object[][] testSearchAddress_positiveDP() {
        Address expectedAddressOne = new Address("Anupratap Colony", "Rawatbhata", 0);
        expectedAddressOne.setId(1l);

        List<Address> expectedListOne = new ArrayList<>();
        expectedListOne.add(expectedAddressOne);

        Address expectedAddressTwo = new Address(null, "Greater Noida", 220504);
        expectedAddressTwo.setId(3l);

        List<Address> expectedListTwo = new ArrayList<>();
        expectedListTwo.add(expectedAddressTwo);

        Address expectedAddressThree = new Address("Vaishali", "Delhi", 0);
        Address expectedAddressFour = new Address("Rajiv Gandhi Nagar", "Kota", 0);

        List<Address> expectedListThree = new ArrayList<>();
        expectedListThree.add(expectedAddressThree);
        expectedListThree.add(expectedAddressFour);

        return new Object[][] {
                                new Object[]{new String[] {"street"},
                                             "pratap",
                                             expectedListOne,
                                             new String[] {"id", "street", "city"}},

                                new Object[]{new String[] {"city"},
                                             "Noi",
                                             expectedListTwo,
                                             new String[] {"id", "city", "pin code"}},

                                new Object[] {new String[] {"street", "city"},
                                              "hi",
                                              expectedListThree,
                                              new String[] {"street", "city"}}
                              };
    }

    @Test(priority = 3, dataProvider = "testReadAddress_positiveDP")
    private void testReadAddress_positive(Address addressId, Address expectedResult) throws Exception {

        ConnectionSetup.setConnection();

        try {
            Address actualResult = addressService.readAddress(addressId);
            Assert.assertEquals(actualResult.toString(), expectedResult.toString());
            ConnectionSetup.releaseConnection(true);
        } catch (Exception e) {
            Assert.fail("Unexpected exception occurred for reading a record");
            ConnectionSetup.releaseConnection(false);
        }
    }

    @DataProvider
    private Object[][] testReadAddress_positiveDP() {
        Address addressIdOne = new Address();
        addressIdOne.setId(1l);

        Address expectedAddressOne = new Address("Anupratap Colony", "Rawatbhata", 323307);
        expectedAddressOne.setId(1l);

        Address addressIdTwo = new Address();
        addressIdTwo.setId(2l);

        Address expectedAddressTwo = new Address("Vaishali", "Delhi", 201204);
        expectedAddressTwo.setId(2l);

        return new Object[][] {
                               {addressIdOne , expectedAddressOne},
                               {addressIdTwo, expectedAddressTwo}
                              };
    }

    @Test(priority = 4, dataProvider = "testReadAllAddress_positiveDP")
    private void testReadAllAddress_positive(List<Address> expectedList) throws Exception  {

        ConnectionSetup.setConnection();

        try {
            List<Address> actualList = addressService.readAllAddress();
            Assert.assertEquals(actualList.toString(), expectedList.toString());
            ConnectionSetup.releaseConnection(true);
        } catch (Exception e) {
            Assert.fail("Unexpected exception occured for reading all records");
            ConnectionSetup.releaseConnection(false);
        }
    }

    @DataProvider
    private Object[][] testReadAllAddress_positiveDP() {
        Address addressFirst = new Address("Anupratap Colony","Rawatbhata",323307);
        addressFirst.setId(1);
        Address addressSecond = new Address("Vaishali", "Delhi", 201204);
        addressSecond.setId(2);
        Address addressThird = new Address("Sector 16", "Greater Noida", 220504);
        addressThird.setId(3);
        Address addressFourth = new Address("Rajiv Gandhi Nagar", "Kota", 323506);
        addressFourth.setId(4);

        List<Address> expectedList = new ArrayList<>();
        expectedList.add(addressFirst);
        expectedList.add(addressSecond);
        expectedList.add(addressThird);
        expectedList.add(addressFourth);

        return new Object[][] {
                               {expectedList}
                              };
    }

    @Test(priority = 5, dataProvider = "testUpdateAddress_positiveDP")
    private void testUpdateAddress_positive(Address addressDetails, Address expectedResult) throws Exception {

        ConnectionSetup.setConnection();

        try {
            Address actualResult = addressService.updateAddress(addressDetails);
            Assert.assertEquals(actualResult.toString(), expectedResult.toString());
            ConnectionSetup.releaseConnection(true);
        } catch (Exception e) {
            Assert.fail("Unexpected exception occured for updating a record");
            ConnectionSetup.releaseConnection(false);
        }
    }

    @DataProvider
    private Object[][] testUpdateAddress_positiveDP() {
        Address addressOne = new Address("Chandni Chowk", "Delhi", 201204);
        addressOne.setId(2l);

        Address expectedAddressOne = new Address("Chandni Chowk", "Delhi", 201204);
        expectedAddressOne.setId(2l);

        Address addressTwo = new Address("Sector 17", "Chandigarh", 220504);
        addressTwo.setId(3l);

        Address expectedAddressTwo = new Address("Sector 17", "Chandigarh", 220504);
        expectedAddressTwo.setId(3l);

        return new Object[][] {
                               {addressOne, expectedAddressOne},

                               {addressTwo, expectedAddressTwo}
                              };
    }

    @Test(priority = 6, dataProvider = "testUpdateAddress_negativeDP")
    private void testUpdateAddress_negative(Address addressDetails,
                                            List<AppErrorCode> expectedException) throws Exception {

        ConnectionSetup.setConnection();

        try {
            addressService.updateAddress(addressDetails);
            Assert.fail("Expected an exception");
            ConnectionSetup.releaseConnection(true);
        } catch (AppException e) {
            Assert.assertEquals(e.getExceptionList().toString(), expectedException.toString());
            ConnectionSetup.releaseConnection(false);
        }
    }

    @DataProvider
    private Object[][] testUpdateAddress_negativeDP() {
        Address addressFirst = new Address("Chandni Chowk", "Delhi", 0);
        addressFirst.setId(2l);

        List<AppErrorCode> expectedExceptionFirst = new ArrayList<>();
        expectedExceptionFirst.add(AppErrorCode.ERROR_NULL_POSTAL_CODE);

        Address addressSecond = new Address("Sector 17", null, 220504);
        addressSecond.setId(3l);

        List<AppErrorCode> expectedExceptionSecond = new ArrayList<>();
        expectedExceptionSecond.add(AppErrorCode.ERROR_NULL_CITY);

        return new Object[][] {
                                 {addressFirst, expectedExceptionFirst},
                                 {addressSecond, expectedExceptionSecond}
                              };
    }

    @Test(priority = 7, dataProvider = "testDeleteAddress_positiveDP")
    private void testDeleteAddress_positive(Address addressDetails,
                                            Address expectedResult) throws Exception {

        ConnectionSetup.setConnection();

        try {
            Address actualResult = addressService.deleteAddress(addressDetails);
            Assert.assertEquals(actualResult.toString(), expectedResult.toString());
            ConnectionSetup.releaseConnection(true);
        } catch (Exception e) {
            Assert.fail("Unexpected exception occured for deleting a record");
            ConnectionSetup.releaseConnection(false);
        }
    }

    @DataProvider
    private Object[][] testDeleteAddress_positiveDP() {
        Address address = new Address("Rajiv Gandhi Nagar", "Kota", 323506);
        address.setId(4l);

        Address expectedResult = new Address("Rajiv Gandhi Nagar", "Kota", 323506);
        expectedResult.setId(4l);

        return new Object[][] {
                               {address, expectedResult}
                              };
    }

    @AfterClass
    private void tearDown() throws Exception {
        addressService = null;
    }
}

