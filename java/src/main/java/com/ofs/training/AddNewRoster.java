/**
 *
 */
package com.ofs.training;

import java.time.chrono.IsoChronology;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mohammed.mohammed
 *
 */
public class AddNewRoster {

    /**
     * Creates a new list of persons
     *
     * @return a list of persons added in this method definition
     */
    private List<Person> createNewRoster() {
        List<Person> newRoster = new ArrayList<>();
        newRoster.add(new Person("John",
                                 IsoChronology.INSTANCE.date(1980, 6, 20),
                                 Person.Sex.MALE,
                                 "john@example.com"));

        newRoster.add(new Person("Jade",
                                 IsoChronology.INSTANCE.date(1990, 7, 15),
                                 Person.Sex.FEMALE, "jade@example.com"));

        newRoster.add(new Person("Donald",
                                 IsoChronology.INSTANCE.date(1991, 8, 13),
                                 Person.Sex.MALE, "donald@example.com"));

        newRoster.add(new Person("Bob",
                                 IsoChronology.INSTANCE.date(2000, 9, 12),
                                 Person.Sex.MALE, "bob@example.com"));

        return newRoster;
    }

    /**
     * Adds a new list to the pre-existing list
     *
     * @param originalRoster
     *        The original list to which new list will be added
     *
     * @param newRoster
     *        The new list to be added to the original roster
     */
    private void addRoster(List<Person> originalRoster, List<Person> newRoster) {
        originalRoster.addAll(newRoster);
    }

    /**
     * @param obj
     *        Result object to be printed
     */
    private void log(Object obj) {
        System.out.println(obj);
    }

    public static void main(String[] args) {
        AddNewRoster addNewRoster = new AddNewRoster();
        List<Person> person = Person.createRoster();
        List<Person> newRoster = addNewRoster.createNewRoster();

        addNewRoster.addRoster(person, newRoster);

        System.out.println("Roster after addition of new roster:");
        addNewRoster.log(person);
        System.out.println();

        System.out.println("Number of persons in the roster after addition: ");
        addNewRoster.log(person.size());
        System.out.println();

        person.removeAll(person);
        System.out.println("Roster after removing all persons:");
        addNewRoster.log(person);
    }
}
