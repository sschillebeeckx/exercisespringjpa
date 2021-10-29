package be.abis.exercise.service;

import be.abis.exercise.mapper.EnrolmentMapper;
import be.abis.exercise.mapper.SessionMapper;
import be.abis.exercise.exception.EnrolException;
import be.abis.exercise.model.*;
import be.abis.exercise.model.dto.EnrolmentDTO;
import be.abis.exercise.model.dto.SessionDTO;
import be.abis.exercise.repository.EnrolmentJpaRepository;
import be.abis.exercise.repository.SessionJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
    public void enrolForSession(Person person, int sessionId) throws EnrolException {

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


}
