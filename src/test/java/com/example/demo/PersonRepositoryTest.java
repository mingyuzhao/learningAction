package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@DataR2dbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @BeforeEach
    public void setup() {
        personRepository.deleteAll().block();
    }

    @Test
    public void testSave() {
        Person person = new Person("John Doe");

        Mono<Person> savedPerson = personRepository.save(person);
        StepVerifier.create(savedPerson)
                .expectNextMatches(p -> p.getId() != null && p.getName().equals("John Doe"))
                .verifyComplete();
    }

    @Test
    public void testSaveAndFindById() {
        Person person = new Person("John Doe");

        Mono<Person> savedPerson = personRepository.save(person);
        Mono<Person> retrievedPerson = savedPerson.flatMap(p -> personRepository.findById(p.getId()));
        StepVerifier.create(retrievedPerson)
                .expectNextMatches(p -> p.getName().equals("John Doe"))
                .verifyComplete();
    }
}
