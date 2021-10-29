package be.abis.exercise.controller;

import be.abis.exercise.exception.ApiError;
import be.abis.exercise.exception.PersonAlreadyExistsException;
import be.abis.exercise.exception.PersonCanNotBeDeletedException;
import be.abis.exercise.exception.PersonNotFoundException;
import be.abis.exercise.model.Login;
import be.abis.exercise.model.Person;
import be.abis.exercise.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("persons")
public class PersonApiController {

    @Autowired
    PersonService personService;

    @Autowired
    PersonService ps;

    @GetMapping("")
    public List<Person> getAllPersons(){
        return ps.getAllPersons();
    }

    @GetMapping("{id}")
    public ResponseEntity<? extends Object> findPerson(@PathVariable("id") int id) {

        try {
            Person p = ps.findPerson(id);
            return new ResponseEntity<Object>(p, HttpStatus.OK);
        } catch (PersonNotFoundException pnfe) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            ApiError err = new ApiError("person not found", status.value(), pnfe.getMessage());
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.add("content-type", MediaType.APPLICATION_PROBLEM_JSON_VALUE);
            return new ResponseEntity<Object>(err, responseHeaders, status);
        }

    }

    @PostMapping("/login")
    public ResponseEntity<Object> findPersonByMailAndPwd(@RequestBody Login login) {

        try {
            Person p = ps.findPerson(login.getEmail(), login.getPassword());
            return new ResponseEntity<Object>(p, HttpStatus.OK);
        } catch (PersonNotFoundException pnfe) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            ApiError err = new ApiError("person not found", status.value(), pnfe.getMessage());
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.add("content-type", MediaType.APPLICATION_PROBLEM_JSON_VALUE);
            return new ResponseEntity<Object>(err, responseHeaders, status);
        }
    }

    @GetMapping(path="/compquery")
    public List<Person> findPersonsByCompanyName(@RequestParam("compname") String compName) {
        return ps.findPersonsByCompanyName(compName);
    }

    @PostMapping(path="")
    public void addPerson(@Valid @RequestBody Person p) throws PersonAlreadyExistsException {
            ps.addPerson(p);
    }

    @DeleteMapping("{id}")
    public void deletePerson(@PathVariable("id") int id) throws PersonCanNotBeDeletedException {
        ps.deletePerson(id);
    }


    @PutMapping("{id}")
    public void changePassword(@PathVariable("id") int id, @Valid @RequestBody Person person)  {
            System.out.println("changing password to newpswd= " + person.getPassword());
            ps.changePassword(person, person.getPassword());
    }





}
