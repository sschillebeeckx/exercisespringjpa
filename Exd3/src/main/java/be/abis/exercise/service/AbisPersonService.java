package be.abis.exercise.service;

import be.abis.exercise.exception.PersonAlreadyExistsException;
import be.abis.exercise.exception.PersonCanNotBeDeletedException;
import be.abis.exercise.exception.PersonNotFoundException;
import be.abis.exercise.model.Address;
import be.abis.exercise.model.Company;
import be.abis.exercise.model.Person;
import be.abis.exercise.repository.AddressJpaRepository;
import be.abis.exercise.repository.CompanyJpaRepository;
import be.abis.exercise.repository.PersonJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbisPersonService implements PersonService {

    @Autowired
    PersonJpaRepository personRepository;

    @Autowired
    AddressJpaRepository addressRepository;

    @Autowired
    CompanyJpaRepository companyRepository;


    @Override
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    @Override
    public Person findPerson(int id) throws PersonNotFoundException {
        Person p = personRepository.findByPersonId(id);
        if (p==null) throw new PersonNotFoundException("person with id " + id + " could not be found.");
        return p;
    }

    public Person findPerson(String email) {
        return personRepository.findByEmailAddress(email);
    }

    @Override
    public Person findPerson(String emailAddress, String passWord) throws PersonNotFoundException {
        Person p = personRepository.findByEmailAddressAndPassword(emailAddress, passWord);
        if (p==null) throw new PersonNotFoundException("Problem login in. Please check your email/password");
        return p;
    }

    @Override
    public List<Person> findPersonsByCompanyName(String compName) {
        return personRepository.getByCompanyName(compName);
    }

    @Override
    public Person addPerson(Person p) throws PersonAlreadyExistsException {
        Person found = this.findPerson(p.getEmailAddress());
        if (found!=null) {
            throw new PersonAlreadyExistsException("You are already registered. Please login");
        }
        Company c = p.getCompany();
        if (c != null) {
            int cono = c.getId();
            if (cono == 0) {
                Address a = c.getAddress();
                Company newComp = null;
                Company foundComp = companyRepository.getByNameAndTown(c.getName(), a.getTown());
                if (foundComp == null) {
                    newComp = companyRepository.save(c);
                } else {
                    newComp = foundComp;
                }
                p.setCompany(newComp);
            }
        }
        Person added = personRepository.save(p);
        return added;
    }

    @Override
    public void deletePerson(int id) throws PersonCanNotBeDeletedException {
        Person p = personRepository.findByPersonId(id);
        if (p==null) throw new PersonCanNotBeDeletedException("Person cannot be deleted since he does not exist.");
        try {
            personRepository.deleteById(id);
        } catch (DataIntegrityViolationException dve){
            throw new PersonCanNotBeDeletedException("You cannot delete this person, since there are still enrolments/sessions for him.");
        }
    }

    @Override
    public void changePassword(Person p, String newPswd)  {
        Person person = personRepository.findByPersonId(p.getPersonId());
        person.setPassword(newPswd);
        personRepository.save(person);
    }


}
