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

import com.objectfrontier.training.ws.connection.ConnectionSetup;
import com.objectfrontier.training.ws.exception.AppErrorCode;
import com.objectfrontier.training.ws.exception.AppException;
import com.objectfrontier.training.ws.pojo.Address;
import com.objectfrontier.training.ws.pojo.Person;
import com.objectfrontier.training.ws.service.AddressService;
import com.objectfrontier.training.ws.service.PersonService;

/**
 * @author mohammed.mohammed
 * @since  Oct 6, 2018
 */
@Test
public class PersonServiceTest {
    PersonService personService;

    @BeforeClass(alwaysRun = true)
    private void setUp() throws Exception {
        AddressService addressService = new AddressService();
        personService = new PersonService(addressService);
    }

    @Test(priority = 1, dataProvider = "testCreatePerson_positiveDP", groups = {"create"})
    private void testCreatePerson_positive(Person personDetails,
                                           Person expectedResult) throws Exception {

        ConnectionSetup.setConnection();

        try {
            Person actualResult = personService.createPerson(personDetails);
            Assert.assertEquals(actualResult.toString(), expectedResult.toString());
            ConnectionSetup.releaseConnection(true);
        } catch (Exception e) {
            Assert.fail("Unexpected exception for inserting values in person table");
            ConnectionSetup.releaseConnection(false);
        }
    }

    @DataProvider
    private Object[][] testCreatePerson_positiveDP() {
        Person personOne = new Person("Almas", "Ansari", "almas@gmail.com", Date.valueOf("1997-06-02"));
        personOne.setAddress(new Address("Anupratap Colony","Rawatbhata",323307));
        personOne.setAdmin(true);
        personOne.setPassword("admin");

        Person expectedPersonOne = new Person("Almas", "Ansari", "almas@gmail.com", Date.valueOf("1997-06-02"));
        expectedPersonOne.setAddress(new Address("Anupratap Colony","Rawatbhata",323307));
        expectedPersonOne.setId(1l);
        expectedPersonOne.getAddress().setId(1l);
        expectedPersonOne.setAdmin(true);
        expectedPersonOne.setPassword("admin");


        Person personTwo = new Person("Mayank", "Verma", "mayank@gmail.com", Date.valueOf("1996-5-26"));
        personTwo.setAddress(new Address("Vaishali", "Delhi", 201204));
        personTwo.setAdmin(false);
        personTwo.setPassword("mayank");

        Person expectedPersonTwo = new Person("Mayank", "Verma", "mayank@gmail.com", Date.valueOf("1996-5-26"));
        expectedPersonTwo.setAddress(new Address("Vaishali", "Delhi", 201204));
        expectedPersonTwo.setId(2l);
        expectedPersonTwo.getAddress().setId(2l);
        expectedPersonTwo.setAdmin(false);
        expectedPersonTwo.setPassword("mayank");

        Person personThree = new Person("Raunak", "Chaudhary", "raunak@gmail.com", Date.valueOf("1996-10-30"));
        personThree.setAddress(new Address("Sector 16", "Greater Noida", 220504));
        personThree.setAdmin(false);
        personThree.setPassword("raunak");

        Person expectedPersonThree = new Person("Raunak", "Chaudhary", "raunak@gmail.com", Date.valueOf("1996-10-30"));
        expectedPersonThree.setAddress(new Address("Sector 16", "Greater Noida", 220504));
        expectedPersonThree.setId(3l);
        expectedPersonThree.getAddress().setId(3l);
        expectedPersonThree.setAdmin(false);
        expectedPersonThree.setPassword("raunak");

        Person personFour = new Person("Rehaan", "Khan", "rehaan@gmail.com", Date.valueOf("1995-12-8"));
        personFour.setAddress(new Address("Rajiv Gandhi Nagar", "Kota", 323506));
        personFour.setAdmin(false);
        personFour.setPassword("rehaan");

        Person expectedPersonFour = new Person("Rehaan", "Khan", "rehaan@gmail.com", Date.valueOf("1995-12-8"));
        expectedPersonFour.setAddress(new Address("Rajiv Gandhi Nagar", "Kota", 323506));
        expectedPersonFour.setId(4l);
        expectedPersonFour.getAddress().setId(4l);
        expectedPersonFour.setAdmin(false);
        expectedPersonFour.setPassword("rehaan");

        Person personFive = new Person("Naveen", "Raj", "naveen@gmail.com", Date.valueOf("1996-07-15"));
        Address addressFive = new Address("Taramani", "Chennai", 205698);
        personFive.setAddress(addressFive);
        personFive.setAdmin(false);
        personFive.setPassword("naveen");

        Person expectedPersonFive = new Person("Naveen", "Raj", "naveen@gmail.com", Date.valueOf("1996-07-15"));
        expectedPersonFive.setAddress(new Address("Taramani", "Chennai", 205698));
        expectedPersonFive.setId(5l);
        expectedPersonFive.getAddress().setId(5l);
        expectedPersonFive.setAdmin(false);
        expectedPersonFive.setPassword("naveen");

        Person personSix = new Person("Hariharan", "Sekar", "hariharan@gmail.com", Date.valueOf("1996-06-26"));
        Address addressSix = new Address("Pallavaram", "Chennai",205532);
        personSix.setAdmin(false);
        personSix.setAddress(addressSix);
        personSix.setPassword("hariharan");

        Person expectedPersonSix = new Person("Hariharan", "Sekar", "hariharan@gmail.com", Date.valueOf("1996-06-26"));
        expectedPersonSix.setAddress(new Address("Pallavaram", "Chennai",205532));
        expectedPersonSix.setId(6l);
        expectedPersonSix.getAddress().setId(6l);
        expectedPersonSix.setAdmin(false);
        expectedPersonSix.setPassword("hariharan");

        return new Object[][] {
                                {personOne, expectedPersonOne},
                                {personTwo, expectedPersonTwo},
                                {personThree, expectedPersonThree},
                                {personFour, expectedPersonFour},
                                {personFive, expectedPersonFive},
                                {personSix, expectedPersonSix}
                              };
    }

    @Test(priority = 2, dataProvider = "testCreatePerson_negativeDP", groups = {"create"})
    private void testCreatePerson_negative(Person personDetails,
                                           List<AppErrorCode> expectedExceptionList) throws Exception {

        ConnectionSetup.setConnection();

        try {
            personService.createPerson(personDetails);
            Assert.fail("Expected an exception");
            ConnectionSetup.releaseConnection(true);
        } catch (AppException e) {
            Assert.assertEquals(e.getExceptionList().toString(), expectedExceptionList.toString());
            ConnectionSetup.releaseConnection(false);
        }
    }

    @DataProvider
    private Object[][] testCreatePerson_negativeDP() {
        Person personFirst = new Person("Almas2", "Ansari2", "almas@gmail.com", Date.valueOf("1998-06-02"));
        personFirst.setAddress(new Address("Anupratap Colony","Rawatbhata",323307));

        List<AppErrorCode> expectedExceptionFirst = new ArrayList<>();
        expectedExceptionFirst.add(AppErrorCode.ERROR_DUPL_EMAIL);

        Person personSecond = new Person("Naveen2",  null, null, Date.valueOf("1996-07-15"));
        Address addressSecond = new Address("Mayur Vihar", "Delhi", 345321);
        personSecond.setAddress(addressSecond);

        List<AppErrorCode> expectedExceptionSecond = new ArrayList<>();
        expectedExceptionSecond.add(AppErrorCode.ERROR_NULL_LAST_NAME);
        expectedExceptionSecond.add(AppErrorCode.ERROR_NULL_EMAIL);

        Person personThird = new Person("Hariharan", "Sekar", "hariharan2@gmail.com", null);
        Address addressThird = new Address("Vigyan Nagar", "", 0);
        personThird.setAddress(addressThird);

        List<AppErrorCode> expectedExceptionThird = new ArrayList<>();
        expectedExceptionThird.add(AppErrorCode.ERROR_DUPL_NAME);
        expectedExceptionThird.add(AppErrorCode.ERROR_NULL_BIRTH_DATE);
        expectedExceptionThird.add(AppErrorCode.ERROR_NULL_CITY);
        expectedExceptionThird.add(AppErrorCode.ERROR_NULL_POSTAL_CODE);

        return new Object[][] {
                                new Object[] {personFirst,
                                              expectedExceptionFirst},

                                new Object[] {personSecond,
                                              expectedExceptionSecond},

                                new Object[] {personThird,
                                              expectedExceptionThird}
                              };
    }

    @Test(priority = 3, dataProvider = "testSearchPerson_positiveDP", groups = {"read"})
    private void testSearchPerson_positive(String[] searchField,
                                           String searchKeyword,
                                           List<Person> expectedResult,
                                           String ...resultSetColumn) throws Exception {

        ConnectionSetup.setConnection();

        try {
            List<Person> actualResult = personService.searchPerson(searchField, searchKeyword, resultSetColumn);
            Assert.assertEquals(actualResult.toString(), expectedResult.toString());
            ConnectionSetup.releaseConnection(true);
        } catch (Exception e) {
            Assert.fail("Unexpected exception occurred for searching a record");
            ConnectionSetup.releaseConnection(false);
        }
    }

    @DataProvider
    private Object[][] testSearchPerson_positiveDP() {
        Person expectedPersonOne = new Person("Almas", null, "almas@gmail.com", Date.valueOf("1997-06-02"));
        expectedPersonOne.setAddress(new Address());
        expectedPersonOne.setId(1l);

        List<Person> expectedListOne = new ArrayList<>();
        expectedListOne.add(expectedPersonOne);

        Person expectedPersonTwo = new Person("Raunak", "Chaudhary", null, null);
        expectedPersonTwo.setAddress(new Address());
        expectedPersonTwo.setId(3l);

        Person expectedPersonThree = new Person("Naveen", "Raj", null, null);
        expectedPersonThree.setAddress(new Address());
        expectedPersonThree.setId(5l);

        List<Person> expectedListTwo = new ArrayList<>();
        expectedListTwo.add(expectedPersonTwo);
        expectedListTwo.add(expectedPersonThree);

        return new Object[][] {
                                new Object[] {new String[] {"birthday"},
                                              "1997",
                                              expectedListOne,
                                              new String[] {"id", "First name", "email", "birthday"}},

                                new Object[] {new String[] {"first name"},
                                              "na",
                                              expectedListTwo,
                                              new String[] {"id", "first name", "Last Name"}}
                              };
    }

    @Test(priority = 4, dataProvider = "testReadPersonValue_positiveDP", groups = {"read"})
    private void testReadPersonValue_positive(Person personId,
                                              boolean includeAddress,
                                              Person expectedResult) throws Exception {

        ConnectionSetup.setConnection();

        try {
            Person actualResult = personService.readPerson(personId, includeAddress);
            Assert.assertEquals(actualResult.toString(), expectedResult.toString());
            ConnectionSetup.releaseConnection(true);
        } catch (Exception e) {
            Assert.fail("Unexpected exception occurred for reading a record");
            ConnectionSetup.releaseConnection(false);
        }
    }

    @DataProvider
    private Object[][] testReadPersonValue_positiveDP() {
        Person personIdOne = new Person();
        personIdOne.setId(1l);

        Person expectedPersonOne = new Person("Almas", "Ansari", "almas@gmail.com", Date.valueOf("1997-06-02"));
        expectedPersonOne.setAddress(new Address("Anupratap Colony", "Rawatbhata", 323307));
        expectedPersonOne.setId(1l);
        expectedPersonOne.getAddress().setId(1l);

        Person personIdTwo = new Person();
        personIdTwo.setId(2l);

        Person expectedPersonTwo = new Person("Mayank", "Verma", "mayank@gmail.com", Date.valueOf("1996-05-26"));
        expectedPersonTwo.setAddress(new Address());
        expectedPersonTwo.setId(2l);
        expectedPersonTwo.getAddress().setId(2l);

        return new Object[][] {
                                new Object[] {personIdOne, true, expectedPersonOne},
                                new Object[] {personIdTwo, false, expectedPersonTwo}
                              };
    }

    @Test(priority = 5, dataProvider = "testReadAllPersonValue_positiveDP", groups = {"read"})
    private void testReadAllPersonValue_positive(boolean includeAddress,
                                                 List<Person> expectedResult) throws Exception {

        ConnectionSetup.setConnection();

        try {
            List<Person> actualResult = personService.readAllPerson(includeAddress);
            Assert.assertEquals(actualResult.toString(), expectedResult.toString());
            ConnectionSetup.releaseConnection(true);
        } catch (Exception e) {
            Assert.fail("Unexpected exception occurred for reading all records");
            ConnectionSetup.releaseConnection(false);
        }
    }

    @DataProvider
    private Object[][] testReadAllPersonValue_positiveDP() {
        Person personOne = new Person("Almas", "Ansari", "almas@gmail.com", Date.valueOf("1997-06-02"));
        personOne.setAddress(new Address("Anupratap Colony", "Rawatbhata",323307));
        personOne.setId(1l);
        personOne.getAddress().setId(1l);

        Person personTwo = new Person("Mayank", "Verma", "mayank@gmail.com", Date.valueOf("1996-5-26"));
        personTwo.setAddress(new Address("Vaishali", "Delhi", 201204));
        personTwo.setId(2l);
        personTwo.getAddress().setId(2l);

        Person personThree = new Person("Raunak", "Chaudhary", "raunak@gmail.com", Date.valueOf("1996-10-30"));
        personThree.setAddress(new Address("Sector 16", "Greater Noida", 220504));
        personThree.setId(3l);
        personThree.getAddress().setId(3l);


        Person personFour = new Person("Rehaan", "Khan", "rehaan@gmail.com", Date.valueOf("1995-12-8"));
        personFour.setAddress(new Address("Rajiv Gandhi Nagar", "Kota", 323506));
        personFour.setId(4l);
        personFour.getAddress().setId(4l);


        Person personFive = new Person("Naveen", "Raj", "naveen@gmail.com", Date.valueOf("1996-07-15"));
        personFive.setAddress(new Address("Taramani", "Chennai", 205698));
        personFive.setId(5l);
        personFive.getAddress().setId(5l);


        Person personSix = new Person("Hariharan", "Sekar", "hariharan@gmail.com", Date.valueOf("1996-06-26"));
        personSix.setAddress(new Address("Pallavaram", "Chennai",205532));
        personSix.setId(6l);
        personSix.getAddress().setId(6l);

        List<Person> expectedListFirst = new ArrayList<>();
        expectedListFirst.add(personOne);
        expectedListFirst.add(personTwo);
        expectedListFirst.add(personThree);
        expectedListFirst.add(personFour);
        expectedListFirst.add(personFive);
        expectedListFirst.add(personSix);

        Person anotherPersonOne = new Person("Almas", "Ansari", "almas@gmail.com", Date.valueOf("1997-06-02"));
        anotherPersonOne.setAddress(new Address());
        anotherPersonOne.setId(1l);
        anotherPersonOne.getAddress().setId(1l);

        Person anotherPersonTwo = new Person("Mayank", "Verma", "mayank@gmail.com", Date.valueOf("1996-5-26"));
        anotherPersonTwo.setAddress(new Address());
        anotherPersonTwo.setId(2l);
        anotherPersonTwo.getAddress().setId(2l);

        Person anotherPersonThree = new Person("Raunak", "Chaudhary", "raunak@gmail.com", Date.valueOf("1996-10-30"));
        anotherPersonThree.setAddress(new Address());
        anotherPersonThree.setId(3l);
        anotherPersonThree.getAddress().setId(3l);

        Person anotherPersonFour = new Person("Rehaan", "Khan", "rehaan@gmail.com", Date.valueOf("1995-12-8"));
        anotherPersonFour.setAddress(new Address());
        anotherPersonFour.setId(4l);
        anotherPersonFour.getAddress().setId(4l);

        Person anotherPersonFive = new Person("Naveen", "Raj", "naveen@gmail.com", Date.valueOf("1996-07-15"));
        anotherPersonFive.setAddress(new Address());
        anotherPersonFive.setId(5l);
        anotherPersonFive.getAddress().setId(5l);

        Person anotherPersonSix = new Person("Hariharan", "Sekar", "hariharan@gmail.com", Date.valueOf("1996-06-26"));
        anotherPersonSix.setAddress(new Address());
        anotherPersonSix.setId(6l);
        anotherPersonSix.getAddress().setId(6l);

        List<Person> expectedListSecond = new ArrayList<>();
        expectedListSecond.add(anotherPersonOne);
        expectedListSecond.add(anotherPersonTwo);
        expectedListSecond.add(anotherPersonThree);
        expectedListSecond.add(anotherPersonFour);
        expectedListSecond.add(anotherPersonFive);
        expectedListSecond.add(anotherPersonSix);

        return new Object[][] {
                               new Object[] {true, expectedListFirst},
                               new Object[] {false, expectedListSecond}
                              };
    }

    @Test(priority = 6, dataProvider = "testUpdatePersonValue_positiveDP", groups = {"update"})
    private void testUpdatePersonValue_positive(Person personDetails,
                                                boolean includeAddress,
                                                Person expectedResult) throws Exception {

        ConnectionSetup.setConnection();

        try {
            Person actualResult = personService.updatePerson(personDetails, includeAddress);
            Assert.assertEquals(actualResult.toString(), expectedResult.toString());
            ConnectionSetup.releaseConnection(true);
        } catch (Exception e) {
            Assert.fail("Unexpected exception occurred for updating a record");
            ConnectionSetup.releaseConnection(false);
        }
    }

    @DataProvider
    private Object[][] testUpdatePersonValue_positiveDP() {
        Person personOne = new Person("Rehaan2", "Khan2", "rehaan.cool@gmail.com", Date.valueOf("1995-12-8"));
        personOne.setAddress(new Address("Rajiv Gandhi Nagar", "Kota", 323506));
        personOne.setId(4l);
        personOne.getAddress().setId(4l);

        Person expectedPersonOne = new Person("Rehaan2", "Khan2", "rehaan.cool@gmail.com", Date.valueOf("1995-12-8"));
        expectedPersonOne.setAddress(new Address("Rajiv Gandhi Nagar", "Kota", 323506));
        expectedPersonOne.setId(4l);
        expectedPersonOne.getAddress().setId(4l);

        Person personTwo = new Person("Raunak", "Chaudhary", "raunak.cool@gmail.com", Date.valueOf("1996-10-30"));
        personTwo.setAddress(new Address("Sector 17", "Chandigarh", 220504));
        personTwo.setId(3l);
        personTwo.getAddress().setId(3l);

        Person expectedPersonTwo = new Person("Raunak", "Chaudhary", "raunak.cool@gmail.com", Date.valueOf("1996-10-30"));
        expectedPersonTwo.setAddress(new Address("Sector 17", "Chandigarh", 220504));
        expectedPersonTwo.setId(3l);
        expectedPersonTwo.getAddress().setId(3l);

        Person personThree = new Person("Naveen", "Raj", "naveen@gmail.com", Date.valueOf("1996-07-15"));
        personThree.setAddress(new Address("Taramani", "Bangalore", 205698));
        personThree.setId(5l);
        personThree.getAddress().setId(5l);

        Person expectedPersonThree = new Person("Naveen", "Raj", "naveen@gmail.com", Date.valueOf("1996-07-15"));
        expectedPersonThree.setAddress(new Address("Taramani", "Bangalore", 205698));
        expectedPersonThree.setId(5l);
        expectedPersonThree.getAddress().setId(5l);

        return new Object[][] {
                               new Object[] {personOne,
                                             false,
                                             expectedPersonOne},

                               new Object[] {personTwo,
                                             true,
                                             expectedPersonTwo},

                               new Object[] {personThree,
                                             true,
                                             expectedPersonThree}
                              };
    }

    @Test(priority = 7, dataProvider = "testUpdatePersonValue_negativeDP", groups = {"update"})
    private void testUpdatePersonValue_negative(Person personDetails,
                                                boolean includeAddress,
                                                List<AppErrorCode> expectedExceptionList) throws Exception {

        ConnectionSetup.setConnection();

        try {
            personService.updatePerson(personDetails, includeAddress);
            Assert.fail("Expected an exception");
            ConnectionSetup.releaseConnection(true);
        } catch (AppException e) {
            Assert.assertEquals(e.getExceptionList().toString(), expectedExceptionList.toString());
            ConnectionSetup.releaseConnection(false);
        }
    }

    @DataProvider
    private Object[][] testUpdatePersonValue_negativeDP() {
        Person personOne = new Person("Rehaan", "Khan", "mayank@gmail.com", Date.valueOf("1995-12-8"));
        personOne.setAddress(new Address("Rajiv Gandhi Nagar", "Kota", 323506));
        personOne.setId(4l);
        personOne.getAddress().setId(4l);

        List<AppErrorCode> expectedExceptionFirst = new ArrayList<>();
        expectedExceptionFirst.add(AppErrorCode.ERROR_DUPL_EMAIL);

        Person personTwo = new Person("Raunak", null, null, Date.valueOf("1996-10-30"));
        personTwo.setAddress(new Address("Sector 17", "Chandigarh", 220504));
        personTwo.setId(3l);
        personTwo.getAddress().setId(3l);

        List<AppErrorCode> expectedExceptionSecond = new ArrayList<>();
        expectedExceptionSecond.add(AppErrorCode.ERROR_NULL_LAST_NAME);
        expectedExceptionSecond.add(AppErrorCode.ERROR_NULL_EMAIL);

        Person personThree = new Person("Naveen", "Raj", "naveen.cool@gmail.com", null);
        personThree.setAddress(new Address("Taramani", null, 0));
        personThree.setId(5l);
        personThree.getAddress().setId(5l);

        List<AppErrorCode> expectedExceptionThird = new ArrayList<>();
        expectedExceptionThird.add(AppErrorCode.ERROR_NULL_BIRTH_DATE);
        expectedExceptionThird.add(AppErrorCode.ERROR_NULL_CITY);
        expectedExceptionThird.add(AppErrorCode.ERROR_NULL_POSTAL_CODE);

        return new Object[][] {
                                new Object[] {personOne,
                                              false,
                                              expectedExceptionFirst},

                                new Object[] {personTwo,
                                              true,
                                              expectedExceptionSecond},

                                new Object[] {personThree,
                                              true,
                                              expectedExceptionThird}
                              };
    }

    @Test(priority = 8, dataProvider = "testDeletePersonValue_positiveDP", groups = {"delete"})
    private void testDeletePersonValue_positive(Person personDetails, Person expectedResult) throws Exception {

        ConnectionSetup.setConnection();

        try {
            Person actualResult = personService.deletePerson(personDetails);
            Assert.assertEquals(actualResult.toString(), expectedResult.toString());
            ConnectionSetup.releaseConnection(true);
        } catch (Exception e) {
            Assert.fail("Unexpected exception occurred for deleting a record");
            ConnectionSetup.releaseConnection(false);
        }
    }

    @DataProvider
    private Object[][] testDeletePersonValue_positiveDP() {
        Person person =  new Person("Raunak", "Chaudhary", "raunak.cool@gmail.com",Date.valueOf("1996-10-30"));
        person.setAddress(new Address("Sector 17", "Chandigarh", 220504));
        person.setId(3l);

        Person expectedPerson = new Person("Raunak", "Chaudhary", "raunak.cool@gmail.com",Date.valueOf("1996-10-30"));
        expectedPerson.setAddress(new Address("Sector 17", "Chandigarh", 220504));
        expectedPerson.getAddress().setId(3l);
        expectedPerson.setId(3l);

        return new Object[][] {
                               new Object[] {person, expectedPerson}
                              };
    }

    @AfterClass()
    private void tearDown() throws Exception {
        personService = null;
    }
}
