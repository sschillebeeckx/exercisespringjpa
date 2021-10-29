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
        return personRepository.findByPersonId(id);
    }

    @Override
    public Person findPerson(String emailAddress, String passWord) throws PersonNotFoundException {
        return personRepository.findByEmailAddressAndPassword(emailAddress, passWord);
    }

    @Override
    public List<Person> findPersonsByCompanyName(String compName) {
        return personRepository.getByCompanyName(compName);
    }

    @Override
    public void addPerson(Person p) throws PersonAlreadyExistsException {
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
        personRepository.save(p);
    }

    @Override
    public void deletePerson(int id) throws PersonCanNotBeDeletedException {
        personRepository.deleteById(id);
    }

    @Override
    public void changePassword(Person p, String newPswd)  {
        Person person = personRepository.findByPersonId(p.getPersonId());
        person.setPassword(newPswd);
        personRepository.save(person);
    }


}
