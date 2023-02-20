package com.rightfindpro.become.testIntern;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

class Student {
    String name;
    Student(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
//        System.out.println(String.valueOf(name));
        return name;
    }

//    https://roytuts.com/override-equals-and-hashcode-method-in-java/
//    Whenever it is invoked on the same object more than once during an execution of a Java application,
//    the hashCode() method must consistently return the same integer, provided no information used in equality comparison on the object is modified.
    @Override
    public int hashCode() {
        System.out.println("hashCode called");
        return Objects.hash(name);
    }


    @Override
    public boolean equals(Object o) {
        System.out.println(getClass() +" "+o.getClass() +" "+this +" "+o);
        if (this == o){
            System.out.println("this is executed");
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            System.out.println("this 1 got executed");
        } ;
        Student student = (Student) o;
//        System.out.println();
        return Objects.equals(name, student.name);
    }


}
public class SetTest {
    public static void main(String[] args) {
        Set<Student> students1 = new HashSet<>();
        students1.add(new Student("ram"));
        students1.add(new Student("ram"));
        students1.add(new Student("hari"));
//        students1.add(new Student("joj"));
        System.out.println(students1);
    }
}
