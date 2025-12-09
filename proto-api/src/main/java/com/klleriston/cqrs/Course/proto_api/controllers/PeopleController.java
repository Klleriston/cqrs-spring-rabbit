package com.klleriston.cqrs.Course.proto_api.controllers;

import com.klleriston.cqrs.Course.proto_api.models.Person;
import com.klleriston.cqrs.Course.proto_api.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/people")
public class PeopleController {
    @Autowired
    private PeopleService peopleService;

    @PostMapping("/create-people/{quantity}")
    public String createPeople(@PathVariable("quantity") int quantity) {
        peopleService.generatePeople(quantity);
        return String.format("%d Pessoas foram gerados com sucesso!", quantity);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Person>> getPeople() {
        return ResponseEntity.ok(this.peopleService.getPeople());
    }

    @PostMapping("/create-person")
    public ResponseEntity<Person> createPerson(Person person) {
        return ResponseEntity.ok(this.peopleService.createPerson(person));

    }

    @DeleteMapping("/delete-person/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable("id") String id) {
        peopleService.deletePerson(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update-person/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable("id") String id, @RequestBody Person person) {
       return ResponseEntity.ok(this.peopleService.updatePerson(id, person));
    }

    @GetMapping("/person-name/{name}")
    public ResponseEntity<List<Person>> getPeopleByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(this.peopleService.getPeopleByName(name));
    }
}
