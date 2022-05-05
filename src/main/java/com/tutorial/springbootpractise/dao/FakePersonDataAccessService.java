package com.tutorial.springbootpractise.dao;

import com.tutorial.springbootpractise.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDao{

    private static List<Person> DB = new ArrayList<>();

    @Override
    public int insertPerson(UUID id, Person person) {
        DB.add(new Person(id, person.getName()));
        return 1;
    }

    @Override
    public List<Person> selectAllPeople() {
        return DB;
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        return DB.stream()
                .filter(person->person.getId().equals(id))
                .findFirst();
    }

    @Override
    public int updatePersonById(UUID id, Person personToUpdate) {
        Integer foundPersonIndex=null;
        for(int i=0; i<DB.size();i++){
            Person person = DB.get(i);
            if(person.getId().equals(id)){
                foundPersonIndex = i;
                break;
            }
        }
        if(foundPersonIndex != null){
            DB.remove((int)foundPersonIndex);
            DB.add(new Person(id, personToUpdate.getName()));
            return 1;
        }else{
            return 0;
        }
    }

    @Override
    public int deletePersonById(UUID id) {
        Person foundPerson=null;
        for(Person person: DB){
            if(person.getId().equals(id)){
                foundPerson = person;
                break;
            }
        }
        if(foundPerson != null){
            DB.remove(foundPerson);
            return 1;
        }else{
            return 0;
        }
    }

}
