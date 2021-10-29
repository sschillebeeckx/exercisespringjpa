package be.abis.exercise.controller;

import be.abis.exercise.model.dto.EnrolmentDTO;
import be.abis.exercise.model.dto.SessionDTO;
import be.abis.exercise.model.Person;
import be.abis.exercise.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SessionApiController {

    @Autowired
    TrainingService trainingService;

    @PostMapping("sessions/{id}/enrolments")
    public void enrollForSession(@RequestBody Person person, @PathVariable("id") int sessionId){

    }

    @GetMapping("sessions/query")
    List<SessionDTO> findSessionsForCourse(@RequestParam("title") String courseTitle){
        return trainingService.findSessionsForCourse(courseTitle);
    }

    @GetMapping("persons/{id}/enrolments")
    public List<EnrolmentDTO> findEnrolments(@PathVariable("id") int personId)
    {
        return trainingService.findEnrolments(personId);
    }


}
