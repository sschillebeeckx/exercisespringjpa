package be.abis.exercise.service;

import be.abis.exercise.exception.EnrolException;
import be.abis.exercise.model.*;
import be.abis.exercise.model.dto.EnrolmentDTO;
import be.abis.exercise.model.dto.SessionDTO;

import java.util.List;

public interface TrainingService {

    public String getWelcomeMessage();
    public PersonService getPersonService();
    public CourseService getCourseService();

    List<SessionDTO> findSessionsForCourse(String courseTitle);

    public void enrolForSession(Person person,int sessionId) throws EnrolException;

    public List<EnrolmentDTO> findEnrolments(int personId);


}
