package be.abis.exercise.repository;

import be.abis.exercise.model.Course;
import be.abis.exercise.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SessionJpaRepository extends JpaRepository<Session,Integer> {

    Session findById(int id);

    @Query("select s from Session s where s.course= :c")
    List<Session> findByCourse(@Param("c") Course c);

    @Query(value = "select sno, sdate, pfname,plname,coname, atown,skind,scancel,cltitle\n" +
                   "from sessions inner join persons on sins_pno=pno\n" +
                   "              inner join companies on sloc_cono=cono\n" +
                   "              inner join addresses on co_ano = aid\n" +
                   "              inner join courses on s_cid = cid\n" +
                   "where cstitle = :title and scancel is null",
            nativeQuery = true)
    List<Object[]> getSessionsForCourse(@Param("title") String courseShortTitle);

}
