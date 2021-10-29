package be.abis.exercise.mapper;

import be.abis.exercise.model.*;
import be.abis.exercise.model.dto.CompanyDTO;
import be.abis.exercise.model.dto.EnrolmentDTO;
import be.abis.exercise.model.dto.PersonDTO;
import be.abis.exercise.repository.CompanyJpaRepository;
import be.abis.exercise.repository.PersonJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EnrolmentMapper {

    @Autowired
    PersonJpaRepository prepo;

    @Autowired
    CompanyJpaRepository crepo;

    public static EnrolmentDTO toDTO(Enrolment e) {
        EnrolmentDTO me = new EnrolmentDTO();
        me.setPerson(PersonMapper.toDTO(e.getEnrollee()));
        me.setEnrolleeCompanyName(e.getEnrollee().getCompany().getName());
        me.setStartDate(e.getSession().getStartDate());
        me.setLocation(CompanyMapper.toDTO(e.getSession().getLocation()));
        me.setCoursetitle(e.getSession().getCourse().getLongTitle());
        return me;
    }

    public static EnrolmentDTO toDTO(Object[] objArray) {
        EnrolmentDTO me = new EnrolmentDTO();
        me.setPerson(new PersonDTO(objArray[0].toString(), objArray[1].toString()));
        me.setEnrolleeCompanyName(objArray[2].toString());

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        me.setStartDate(LocalDate.parse(objArray[3].toString(), dtf));

        me.setLocation(new CompanyDTO(objArray[4].toString(), objArray[5].toString()));

        me.setCoursetitle(objArray[6].toString());

        return me;
    }
}
