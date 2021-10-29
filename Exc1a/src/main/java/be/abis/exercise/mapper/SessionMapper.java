package be.abis.exercise.mapper;

import be.abis.exercise.model.dto.SessionDTO;
import be.abis.exercise.model.Session;
import be.abis.exercise.repository.CompanyJpaRepository;
import be.abis.exercise.repository.PersonJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class SessionMapper {

    @Autowired
    PersonJpaRepository prepo;

    @Autowired
    CompanyJpaRepository crepo;

    public static SessionDTO toDTO(Session s){
        SessionDTO ms = new SessionDTO();
        ms.setSessionNumber(s.getSessionId());
        ms.setStartDate(s.getStartDate());
        ms.setInstructor(PersonMapper.toDTO(s.getInstructor()));
        ms.setLocation(CompanyMapper.toDTO(s.getLocation()));
        ms.setKind(s.getKind());
        ms.setCancelled(s.isCancelled());
        ms.setCourseTitle(s.getCourse().getLongTitle());
        return ms;
    }
}
