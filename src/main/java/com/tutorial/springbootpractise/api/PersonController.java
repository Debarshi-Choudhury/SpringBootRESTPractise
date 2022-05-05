package com.tutorial.springbootpractise.api;

import com.tutorial.springbootpractise.model.Person;
import com.tutorial.springbootpractise.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/person")
@RestController
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService){
        this.personService = personService;
    }

    @PostMapping
    public void addPerson(@RequestBody Person person){
        personService.addPerson(person);
    }

    @GetMapping
    public List<Person> selectAllPeople(){
        return personService.selectAllPeople();
    }

    @GetMapping(path="{id}")
    public Person selectPersonById(@PathVariable("id") UUID id){
        return personService.selectPersonById(id)
                .orElse(null);
    }

    @PutMapping(path="{id}")
    public void updatePersonById(@PathVariable("id") UUID id, @RequestBody Person person){
        personService.updatePersonById(id, person);
    }

    @DeleteMapping(path="{id}")
    public void deletePersonById(@PathVariable("id") UUID id){
        personService.deletePersonById(id);
    }

}
