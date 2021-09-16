package com.company;

import java.util.*;
import java.util.stream.Collectors;

public class MainClass {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        Long lessThen18 = persons.stream().filter(x -> x.getAge() < 18).count();
        System.out.println(lessThen18);

        List<String> list = persons.stream()
                .filter(x-> (x.getAge()>=18) && (x.getAge()<=27))
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println(list);

        List<Person> manPersons = persons.stream()
                .filter(s-> s.getSex() == Sex.MAN)
                .filter(c -> c.getAge()>=18 && c.getAge()<=65)
                .filter(c -> c.getEducation() == Education.HIGHER)
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());

        System.out.println(manPersons);

        List<Person> womanPersons = persons.stream()
                .filter(s-> s.getSex() == Sex.WOMAN)
                .filter(c -> c.getAge()>=18 && c.getAge()<=60)
                .filter(c -> c.getEducation() == Education.HIGHER)
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());

        System.out.println(womanPersons);
    }
}
