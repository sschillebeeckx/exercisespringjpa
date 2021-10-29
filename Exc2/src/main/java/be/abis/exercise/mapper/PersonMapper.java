package be.abis.exercise.mapper;

import be.abis.exercise.model.dto.PersonDTO;
import be.abis.exercise.model.Person;

public class PersonMapper {

    public static PersonDTO toDTO(Person person){
        return new PersonDTO(person.getFirstName(), person.getLastName());
    }
}
