/**
 *
 */
package com.objectfrontier.training.ws.util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.objectfrontier.training.ws.pojo.Address;
import com.objectfrontier.training.ws.pojo.Person;

/**
 * @author mohammed.mohammed
 * @since  Nov 17, 2018
 */
public class CsvDataReader {

    public static List<Address> readAddressCsvFile() throws IOException, URISyntaxException {

        URL fileLocation = CsvDataReader.class.getResource("addressData.csv");
            Path filePath = Paths.get(fileLocation.toURI());
            List<String> content = Files.readAllLines(filePath);
            List<Address> addressDataList = new ArrayList<>();

            for(String contentLine :content) {
                String[] dataElement = contentLine.split(",");
                addressDataList.add(new Address(dataElement[0],
                                            dataElement[1],
                                            Integer.parseInt(dataElement[2])));
            }
            return addressDataList;
    }

    public static List<Person> readPersonCsvFile() throws IOException, URISyntaxException {

        URL fileLocation = CsvDataReader.class.getResource("personData.csv");
        Path filePath = Paths.get(fileLocation.toURI());
        List<String> content = Files.readAllLines(filePath);
        List<Person> personDataList = new ArrayList<>();

        for(String contentLine :content) {
            String[] dataElement = contentLine.split(",");
            Person personDetails = new Person(dataElement[0],
                                              dataElement[1],
                                              dataElement[2],
                                              Date.valueOf(dataElement[3]));

            personDetails.setAdmin(Boolean.parseBoolean(dataElement[4]));
            personDetails.setPassword(dataElement[5]);

            Address addressDetails = new Address(dataElement[6],
                                                 dataElement[7],
                                                 Integer.parseInt(dataElement[8]));

            personDetails.setAddress(addressDetails);
            personDataList.add(personDetails);
        }
        return personDataList;
    }
}
