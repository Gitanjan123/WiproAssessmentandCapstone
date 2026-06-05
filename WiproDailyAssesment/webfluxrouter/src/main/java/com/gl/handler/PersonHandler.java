package com.gl.handler;

import org.springframework.stereotype.Component;

import com.gl.model.Person;
import com.gl.repository.PersonRepository;

import reactor.core.publisher.Mono;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;
import static org.springframework.web.reactive.function.server.ServerResponse.noContent;
import static org.springframework.web.reactive.function.server.ServerResponse.notFound;

@Component
public class PersonHandler {

    private final PersonRepository personRepository;

    public PersonHandler(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    // GET /persons/{id}
    public Mono<ServerResponse> getPerson(ServerRequest request) {
        String id = request.pathVariable("id");
        return personRepository.findById(id)
                .flatMap(person -> ok().bodyValue(person))
                .switchIfEmpty(notFound().build());
    }

    // GET /persons
    public Mono<ServerResponse> getAllPersons(ServerRequest request) {
        return ok().body(personRepository.findAll(), Person.class);
    }

    // POST /persons
    public Mono<ServerResponse> createPerson(ServerRequest request) {
        return request.bodyToMono(Person.class)
                .flatMap(person -> personRepository.save(person))
                .flatMap(person -> ok().bodyValue(person))
                .switchIfEmpty(notFound().build());
    }

    // PUT /persons/{id}
    public Mono<ServerResponse> updatePerson(ServerRequest request) {
        String id = request.pathVariable("id");
        return request.bodyToMono(Person.class)
                .flatMap(person ->
                        personRepository.update(id, person)
                                .flatMap(updatedPerson -> ok().bodyValue(updatedPerson))
                                .switchIfEmpty(notFound().build())
                );
    }

    // DELETE /persons/{id}
    public Mono<ServerResponse> deletePerson(ServerRequest request) {
        String id = request.pathVariable("id");
        return personRepository.findById(id)
                .flatMap(person -> personRepository.delete(id))
                .then(noContent().build())
                .switchIfEmpty(notFound().build());
    }
}