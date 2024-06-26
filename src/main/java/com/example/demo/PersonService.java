package com.example.demo;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PersonService {
    private PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Flux<Person> findAll() {
        return personRepository.findAll();
    }

    public Mono<Person> save(Person person) {
        Mono<Person> result = personRepository.save(person);
        return personRepository.save(person);
    }

    public Mono<Person> findById(Long id) {
        return personRepository.findById(id);
    }

    public Mono<Void> deleteById(Long id) {
        return personRepository.deleteById(id);
    }
}
