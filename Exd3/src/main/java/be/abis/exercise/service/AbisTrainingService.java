package be.abis.exercise.service;

import be.abis.exercise.mapper.EnrolmentMapper;
import be.abis.exercise.mapper.SessionMapper;
import be.abis.exercise.exception.EnrolException;
import be.abis.exercise.exception.PersonAlreadyExistsException;
import be.abis.exercise.model.*;
import be.abis.exercise.model.dto.EnrolmentDTO;
import be.abis.exercise.model.dto.SessionDTO;
import be.abis.exercise.repository.EnrolmentJpaRepository;
import be.abis.exercise.repository.SessionJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class AbisTrainingService implements TrainingService {

    @Value("Welcome to the Abis Training Service")
    private String welcomeMessage;

    @Autowired
    private PersonService personService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private SessionJpaRepository sessionRepository;

    @Autowired
    private EnrolmentJpaRepository enrolmentRepository;

    @Override
    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    public void setWelcomeMessage(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }

    public PersonService getPersonService() {
        return personService;
    }

    public CourseService getCourseService() {
        return courseService;
    }


    @Override
    @Transactional(rollbackOn = EnrolException.class)
    public void enrolForSession(Person person, int sessionId) throws EnrolException {

        Person personToEnrol = null;
        try {
            personToEnrol = personService.addPerson(person);
        } catch (PersonAlreadyExistsException e) {
            personToEnrol = personService.findPerson(person.getEmailAddress());
        }

        System.out.println("person to enrol: " + personToEnrol);
        Session s = sessionRepository.findById(sessionId);
        //System.out.println("session found: " + s.getCourse().getLongTitle());
        if (s == null) throw new EnrolException("Can not enrol, session does not exist");
        Integer eno = enrolmentRepository.getLastEnoForSession(sessionId);
        int newEno = (eno==null)?1:eno+1;
       // double priceToPay = getCourseService().findCourse(s.getCourse().getCourseId()).getPricePerDay();
        try {
            enrolmentRepository.saveEnrolment(s.getSessionId(), newEno, personToEnrol.getPersonId(), s.getCourse().getPricePerDay(), personToEnrol.getCompany().getId());
        } catch (DataAccessException dae){
            throw new EnrolException("oops, something went wrong");
        }
    }

    @Override
    public List<SessionDTO> findSessionsForCourse(String courseTitle) {
        List<SessionDTO> msessions = new ArrayList<>();
        List<Object[]> sessions = sessionRepository.getSessionsForCourse(courseTitle);
        for (Object[] objArray: sessions){
            SessionDTO ms = SessionMapper.toDTO(objArray);
            msessions.add(ms);
        }
        return msessions;
    }


    @Override
    public List<EnrolmentDTO> findEnrolments(int personId) {
        List<Object[]> enrolments = enrolmentRepository.findByEnrolleeNQ(personId);
        List<EnrolmentDTO> menrolments = new ArrayList<>();
        for (Object[] e: enrolments){
            menrolments.add(EnrolmentMapper.toDTO(e));
        }

        return menrolments;
    }

    @Override
    @Transactional
    public void cancelSession(int sessionId) {
        sessionRepository.cancelSession(sessionId);
    }
}
