/**
 *
 */
package com.ofs.training;

import java.util.Iterator;
import java.util.List;

/**
 * @author mohammed.mohammed
 *
 */
public class ListIterator {

    /**
     * @param person
     *        Object of list of all persons
     */
    private void iterateList(List<Person> person) {
        Iterator<Person> iterate = person.iterator();

        iterate.forEachRemaining(personList -> log(personList));

//        while( iterate.hasNext()) {
//           Person personDetail = iterate.next();
//           log(personDetail);
//        }
    }

    /**
     * @param obj
     *        Result object to be printed
     */
    private void log(Object obj) {
        System.out.print(obj);
    }

    public static void main(String[] args) {
         ListIterator listIterator = new ListIterator();
         List<Person> person = Person.createRoster();

         listIterator.iterateList(person);
    }
}
