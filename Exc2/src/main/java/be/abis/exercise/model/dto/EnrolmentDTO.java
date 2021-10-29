package be.abis.exercise.model.dto;

import java.time.LocalDate;

public class EnrolmentDTO {

    private PersonDTO person;
    private String enrolleeCompanyName;
    private LocalDate startDate;
    private CompanyDTO location;
    private String coursetitle;

    public PersonDTO getPerson() {
        return person;
    }

    public void setPerson(PersonDTO person) {
        this.person = person;
    }

    public String getEnrolleeCompanyName() {
        return enrolleeCompanyName;
    }

    public void setEnrolleeCompanyName(String enrolleeCompanyName) {
        this.enrolleeCompanyName = enrolleeCompanyName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public CompanyDTO getLocation() {
        return location;
    }

    public void setLocation(CompanyDTO location) {
        this.location = location;
    }

    public String getCoursetitle() {
        return coursetitle;
    }

    public void setCoursetitle(String coursetitle) {
        this.coursetitle = coursetitle;
    }
}
