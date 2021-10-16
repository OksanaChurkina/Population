package com.company;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainClass{

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

        List<String> from18To27 = persons.stream()
                .filter(x-> (x.getAge()>=18) && (x.getAge()<27))
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println(from18To27);


        List<Person> from18To65 = persons.stream()
                .filter(s-> s.getSex() == Sex.MAN && s.getAge()>=18 && s.getAge()<65)
                .filter(c -> c.getEducation() == Education.HIGHER)
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());

        List<Person> from18To60 = persons.stream()
                .filter(s-> s.getSex() == Sex.WOMAN && s.getAge()>=18 && s.getAge()<60)
                .filter(c -> c.getEducation() == Education.HIGHER)
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());

        List<Person> newPersonList = Stream.concat(from18To65.stream(), from18To60.stream()).collect(Collectors.toList());
        System.out.println(newPersonList);
    }
}
