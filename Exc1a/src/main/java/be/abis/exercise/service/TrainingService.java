package be.abis.exercise.service;

import be.abis.exercise.exception.EnrolException;
import be.abis.exercise.model.Enrolment;
import be.abis.exercise.model.Session;
import be.abis.exercise.model.dto.SessionDTO;
import be.abis.exercise.model.Person;

import java.util.List;

public interface TrainingService {

    public String getWelcomeMessage();
    public PersonService getPersonService();
    public CourseService getCourseService();

    List<SessionDTO> findSessionsForCourse(String courseTitle);

    public void enrolForSession(Person person, int sessionId) throws EnrolException;

    public List<Enrolment> findEnrolments(int personId);


}
