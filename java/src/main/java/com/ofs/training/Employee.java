// create a Employee class with properties: id and name,
// a constructor which initializes name, a mechanism to automatically assign id by incrementing
// the previous id. Create and print 10 employee objects using Object.toString()
package com.ofs.training;

import java.util.ArrayList;

public class Employee {

    private static int count = 0;

    private int id;
    private String name;

    Employee(String name) {
        this.id = ++count;
        this.name = name;
    }

    private static void log(Object obj) {
        System.out.println(obj);
    }

//    @Override
//    public String toString() {
//        return "Employee id: "
//                + id
//                + "   "
//                + "Employee Name: "
//                + name
//                + "\n";
//    }

    @Override
    public String toString() {
        return String.format("Employee [id=%s, name=%s]\n", id, name);
    }

    public static void main(String[] args) {

        ArrayList<Employee> staff = new ArrayList<>(10);
//        Employee[] staff = new Employee[10];

        staff.add(new Employee("Almas"));
        staff.add(new Employee("Mayank"));
        staff.add(new Employee("Vaibhav"));
        staff.add(new Employee("Adil"));
        staff.add(new Employee("Ronnie"));
        staff.add(new Employee("Akash"));
        staff.add(new Employee("Gangal"));
        staff.add(new Employee("Bansal"));
        staff.add(new Employee("Nirbhay"));
        staff.add(new Employee("Tony"));

        log(staff.get(0));
        log(staff.get(0));
        log(staff);
    }
}
