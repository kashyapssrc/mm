/**
 *
 */
package com.ofs.training;

import java.util.Arrays;
import java.util.List;

/**
 * @author mohammed.mohammed
 * @since  Sep 26, 2018
 */
public class CollectionArrayConvertor {

    /**
     * @param args
     */
    public static void main(String[] args) {
        List<Person> personList = Person.createRoster();
        System.out.println("Person list converted to array:");
        Object[] rosterArray = personList.toArray();
        for (Object arrayElement : rosterArray) {
            log("%s", arrayElement.toString());
        }

        System.out.println();
        System.out.println("Person array converted to list:");
        List<Object> rosterList = Arrays.asList(rosterArray);
        rosterList.stream().forEach(list -> log("%s", list));
    }

    private static void log(String printFormat, Object ...values) {
        System.out.format(printFormat, values);
    }

}
