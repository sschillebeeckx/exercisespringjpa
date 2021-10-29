package be.abis.exercise.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class SessionDTO {

    private int sessionNumber;
    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate startDate;
    @JsonProperty("instructor")
    private PersonDTO instructor;
    @JsonProperty("location")
    private CompanyDTO location;
    private String kind;
    private boolean cancelled;
    private String courseTitle;


    public int getSessionNumber() {
        return sessionNumber;
    }

    public void setSessionNumber(int sessionNumber) {
        this.sessionNumber = sessionNumber;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public PersonDTO getInstructor() {
        return instructor;
    }

    public void setInstructor(PersonDTO instructor) {
        this.instructor = instructor;
    }

    public CompanyDTO getLocation() {
        return location;
    }

    public void setLocation(CompanyDTO location) {
        this.location = location;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }
}
