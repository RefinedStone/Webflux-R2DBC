package com.example.reactiveapp.person;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;

    public Flux<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public Mono<Person> createPerson(PersonRequestDto requestDto) {
        /*기존 방식*/
//        Person person = new Person(requestDto);
//        return personRepository.save(person);

        /*reactive한 방식*/
        /*reactor의 publisher, subscription, subscriber 개념을 이해하자 */
        return Mono.fromSupplier(() -> new Person(requestDto)).flatMap(personRepository::save);
    }
}
