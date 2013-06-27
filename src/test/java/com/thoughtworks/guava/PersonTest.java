package com.thoughtworks.guava;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.sun.istack.internal.Nullable;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Iterables.filter;
import static com.google.common.collect.Iterables.transform;
import static com.google.common.collect.Lists.newArrayList;
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
    }

    @Test
    public void should_get_correct_people() throws Exception {

        List<Person> filterPeople = new ArrayList<Person>();
        for (Person person : people) {
            if (person.getAge() >= 20) {
                filterPeople.add(person);
            }
        }

        assertThat(filterPeople.size(), is(3));
    }

    @Test
    public void should_get_correct_people_by_guava() throws Exception {
        List<Person> filterPeople = newArrayList(filter(people, new Predicate<Person>() {
            public boolean apply(@Nullable com.thoughtworks.guava.Person person) {
                return person.getAge() >= 20;
            }
        }));

        assertThat(filterPeople.size(), is(3));
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
        List<String> names = newArrayList(transform(people, new Function<Person, String>() {
            public String apply(@Nullable Person person) {
                return person.getName();
            }
        }));

        assertThat(names.size(), is(4));
        assertThat(names.get(0), is("bowen"));
    }


    @Test
    public void should_get_whole_ages_of_people() throws Exception {
        int ages = 0;
        for (Person person: people) {
            ages += person.getAge();
        }

        assertThat(ages, is(89));
    }

    @Test
    public void should_get_whole_ages_of_people_by_guava() throws Exception {

    }

}
