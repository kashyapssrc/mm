/**
 *
 */
package com.ofs.training;

import java.util.List;

/**
 * @author mohammed.mohammed
 *
 */
public class AverageAge {

    /**
     * @param person
     *        Object of list of all persons
     *
     * @param personList
     *          Object of list of people for finding age
     */
    private Double calculateAverageAge(List<Person> person) {
        return person.stream()
                     .mapToDouble(personList -> { return personList.getAge(); })
                     .average()
                     .getAsDouble();
    }

    /**
     * @param obj
     *       Result object to be printed
     */
    private void log(Object obj) {
        System.out.println(obj);
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        AverageAge averageAge = new AverageAge();
        List<Person> person = Person.createRoster();

        System.out.println("Average of age of all the persons in the list:");
        averageAge.log(averageAge.calculateAverageAge(person));
    }
}
