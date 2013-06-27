package com.thoughtworks.guava;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Predicates.and;
import static com.google.common.collect.FluentIterable.from;
import static com.google.common.collect.Iterables.filter;
import static com.google.common.collect.Iterables.transform;
import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PersonTest {

    private List<Person> people;

    @Before
    public void setUp() throws Exception {
        people = newArrayList(new Person("bowen", 27),
                new Person("bob", 20),
                new Person("Katy", 18),
                new Person("Logon", 24));

        List<Person> peoples = new ArrayList<Person>();
        peoples.add(new Person("bowen", 27));
        peoples.add(new Person("bob", 20));
        peoples.add(new Person("Katy", 18));
        peoples.add(new Person("Logon", 24));
    }

    @Test
    public void should_get_correct_people() throws Exception {


        List<Person> oldPeople = new ArrayList<Person>();
        for (Person person : people) {
            if (person.getAge() >= 20) {
                oldPeople.add(person);
            }
        }

        assertThat(oldPeople.size(), is(3));
    }

    @Test
    public void should_get_correct_people_by_guava() throws Exception {
        List<Person> oldPeople = newArrayList(filter(people, ageBiggerThan(20)));

        List<Person> namedPeople = newArrayList(filter(people, nameContains("b")));


        List<Person> filteredPeople = newArrayList(filter(people, and(ageBiggerThan(20), nameContains("b"))));


        assertThat(oldPeople.size(), is(3));
    }

    private Predicate<Person> nameContains(final String str) {
        return new Predicate<Person>() {
            public boolean apply(Person person) {
                return person.getName().contains(str);
            }
        };
    }

    private Predicate<Person> ageBiggerThan(final int age) {
        return new Predicate<Person>() {
            public boolean apply(Person person) {
                return person.getAge() >= age;
            }
        };
    }

    @Test
    public void should_get_whole_names_of_people() throws Exception {
        List<String> names = newArrayList();
        for (Person person : people) {
            names.add(person.getName());
        }

        assertThat(names.size(), is(4));
        assertThat(names.get(0), is("bowen"));
    }

    @Test
    public void should_get_whole_names_of_people_by_guava() throws Exception {
        List<String> names = newArrayList(transform(people, getName()));

        assertThat(names.size(), is(4));
        assertThat(names.get(0), is("bowen"));
    }

    private Function<Person, String> getName() {
        return new Function<Person, String>() {
            public String apply(Person person) {
                return person.getName();
            }
        };
    }

    @Test
    public void should_get_whole_ages_of_people() throws Exception {
        int ages = 0;
        for (Person person : people) {
            ages += person.getAge();
        }

        assertThat(ages, is(89));
    }

    @Test
    public void should_get_whole_ages_of_people_by_guava() throws Exception {
        Integer ages = Reduce.reduce(people, new Func<Person, Integer>() {

            public Integer apply(Person person, Integer origin) {
                return person.getAge() > origin ? person.getAge() : origin;
            }
        }, 0);


        assertThat(ages, is(27));
    }

    @Test
    public void getPeopleNamesByAge() {

        List<String> filterNames = newArrayList(transform(filter(people, ageBiggerThan(20)), getName()));

        List<String> filterNames2 = from(people).filter(ageBiggerThan(20)).transform(getName()).toList();
        assertThat(filterNames,equalTo(filterNames2));
    }

}
