package com.example.reactiveapp.person;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/persons")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping
    public Flux<Person> getAllPersons() {
        return personService.getAllPersons();
    }
    @PostMapping
    public Mono<Person> createPerson(@RequestBody PersonRequestDto requestDto) {
        return personService.createPerson(requestDto);
    }
}
