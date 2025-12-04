package com.demo.basics;

import java.time.LocalDate;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class App {
    public static void main(String[] args) {
        System.out.println("== Basic Java Syntax Demo ==");
        primitives();
        controlFlow();
        collectionsAndStreams();
        objectOriented();
        exceptionHandling();
    }

    private static void primitives() {
        System.out.println("-- Primitives and Strings --");
        int wholeNumber = 42;
        double pi = Math.PI;
        boolean isActive = true;
        char letter = 'J';
        String message = "Hello, Java " + LocalDate.now().getYear();

        int sum = wholeNumber + 8;
        double rounded = Math.round(pi * 100.0) / 100.0;

        System.out.printf("int: %d, double: %.2f, boolean: %b, char: %c%n", wholeNumber, rounded, isActive, letter);
        System.out.println("String message: " + message);
        System.out.println("Array literals: " + List.of(1, 2, 3, 4, 5));
    }

    private static void controlFlow() {
        System.out.println("-- Control Flow --");
        for (int i = 0; i < 3; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        int score = new Random().nextInt(101);
        String grade;
        if (score >= 90) {
            grade = "A";
        } else if (score >= 75) {
            grade = "B";
        } else if (score >= 60) {
            grade = "C";
        } else {
            grade = "D";
        }
        System.out.println("Score " + score + " earns grade " + grade);

        int day = 3;
        String dayName = switch (day) {
            case 1 -> "Monday";
            case 2 -> "Tuesday";
            case 3 -> "Wednesday";
            case 4 -> "Thursday";
            case 5 -> "Friday";
            default -> "Weekend";
        };
        System.out.println("Switch expression day: " + dayName);
    }

    private static void collectionsAndStreams() {
        System.out.println("-- Collections, Generics, and Streams --");
        List<String> names = new ArrayList<>(List.of("Alice", "Bob", "Charlie", "Dana"));
        names.add("Eve");
        names.remove("Bob");

        Map<String, Integer> nameToLength = new HashMap<>();
        for (String name : names) {
            nameToLength.put(name, name.length());
        }
        System.out.println("Map values: " + nameToLength);

        Box<Integer> boxed = new Box<>(7);
        System.out.println("Generic box value: " + boxed.get());

        String joined = names.stream()
                .filter(n -> n.startsWith("A") || n.startsWith("C"))
                .sorted()
                .collect(Collectors.joining(", "));
        System.out.println("Stream filtered names: " + joined);

        List<Integer> squares = IntStream.rangeClosed(1, 5)
                .map(n -> n * n)
                .boxed()
                .toList();
        System.out.println("Squares: " + squares);

        ArrayDeque<String> deque = new ArrayDeque<>();
        deque.push("first");
        deque.push("second");
        deque.push("third");
        System.out.println("Stack-like deque pop order: " + deque.pop() + ", " + deque.pop() + ", " + deque.pop());
    }

    private static void objectOriented() {
        System.out.println("-- Object-Oriented Features --");
        Person person = new Person("Sam", 30, Role.USER);
        Describable describable = person;
        System.out.println(describable.describe());

        Employee engineer = new Employee("Pat", 28, Role.ADMIN, "Engineering");
        System.out.println(engineer.describe());

        Comparator<Person> byAge = Comparator.comparingInt(Person::getAge);
        List<Person> team = new ArrayList<>(List.of(person, engineer, new Person("Riley", 25, Role.USER)));
        team.sort(byAge);
        System.out.println("Sorted by age: " + team);
    }

    private static void exceptionHandling() {
        System.out.println("-- Exceptions --");
        try {
            int result = riskyDivide(10, 0);
            System.out.println("Division result: " + result);
        } catch (ArithmeticException ex) {
            System.out.println("Caught exception: " + ex.getMessage());
        } finally {
            System.out.println("Finally blocks always run.");
        }

        Optional<String> maybeName = Optional.of("Optional example");
        System.out.println("Optional value: " + maybeName.orElse("fallback"));
    }

    private static int riskyDivide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        return a / b;
    }

    private static class Person implements Describable {
        private final String name;
        private final int age;
        private final Role role;

        Person(String name, int age, Role role) {
            this.name = name;
            this.age = age;
            this.role = role;
        }

        public int getAge() {
            return age;
        }

        @Override
        public String describe() {
            return name + " (" + age + ") - role=" + role;
        }

        @Override
        public String toString() {
            return describe();
        }
    }

    private static class Employee extends Person {
        private final String department;

        Employee(String name, int age, Role role, String department) {
            super(name, age, role);
            this.department = department;
        }

        @Override
        public String describe() {
            return super.describe() + ", dept=" + department;
        }
    }

    private interface Describable {
        String describe();
    }

    private enum Role {
        ADMIN,
        USER
    }

    private static class Box<T> {
        private final T value;

        Box(T value) {
            this.value = value;
        }

        T get() {
            return value;
        }
    }
}
