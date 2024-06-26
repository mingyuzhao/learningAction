package com.example.demo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FlatMapExample {

    public static void main(String[] args) {
        Flux<Integer> numbers = Flux.just(1, 2, 3);

        // Using flatMap to asynchronously process each number
        Flux<String> result = numbers.flatMap(number -> processNumber(number));

        result.subscribe(System.out::println);


        Flux<String> names = Flux.just("Alice", "Bob", "Charlie");

        // Simulate fetching details for each name asynchronously
        Flux<String> details = names.flatMap(name -> getDetails(name));

        details.subscribe(System.out::println);
    }

    private static Mono<String> processNumber(int number) {
        return Mono.just("Number: " + number);
    }

    private static Mono<String> getDetails(String name) {
        // Simulate an asynchronous operation
        return Mono.just("Details of " + name);
    }
}