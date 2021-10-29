package be.abis.exercise.mapper;

import be.abis.exercise.model.dto.CompanyDTO;
import be.abis.exercise.model.dto.PersonDTO;
import be.abis.exercise.model.dto.SessionDTO;
import be.abis.exercise.model.Session;
import be.abis.exercise.repository.CompanyJpaRepository;
import be.abis.exercise.repository.PersonJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SessionMapper {

    @Autowired
    PersonJpaRepository prepo;

    @Autowired
    CompanyJpaRepository crepo;

    public static SessionDTO toDTO(Session s) {
        SessionDTO ms = new SessionDTO();
        ms.setSessionNumber(s.getSessionId());
        ms.setStartDate(s.getStartDate());


        ms.setInstructor(PersonMapper.toDTO(s.getInstructor()));
        ms.setLocation(CompanyMapper.toDTO(s.getLocation()));
        ms.setKind(s.getKind());
        ms.setCancelled(s.isCancelled());
        ms.setCourseTitle(s.getCourse().getShortTitle());
        return ms;
    }

    public static SessionDTO toDTO(Object[] objArray) {
        SessionDTO ms = new SessionDTO();

        ms.setSessionNumber(Integer.parseInt(objArray[0].toString()));

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        ms.setStartDate(LocalDate.parse(objArray[1].toString(), dtf));

        ms.setInstructor(new PersonDTO(objArray[2].toString(), objArray[3].toString()));
        ms.setLocation(new CompanyDTO(objArray[4].toString(), objArray[5].toString()));
        ms.setKind(objArray[6].toString());

        Object obj = objArray[7];
        ms.setCancelled((obj == null) ? false : true);

        ms.setCourseTitle(objArray[8].toString());

        return ms;
    }
}
