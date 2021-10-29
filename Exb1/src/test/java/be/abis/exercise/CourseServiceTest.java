package be.abis.exercise;

import be.abis.exercise.model.Course;
import be.abis.exercise.service.CourseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CourseServiceTest {

    @Autowired
    CourseService courseService;


    @Test
    public void sizeListIs24(){
        List<Course> allCourses = courseService.findAllCourses();
        assertEquals(24,allCourses.size());
    }

    @Test
    public void course7850IsDB2BAS() {
        int id = 7850;
        Course found = courseService.findCourse(id);
        assertEquals("DB2BAS", found.getShortTitle());
    }

    @Test
    public void courseSQLWStakes3Days() {
        String shortTitle = "SQLWS";
        Course found = courseService.findCourse(shortTitle);
        assertEquals(3,found.getNumberOfDays());
    }

    @Test
    @Transactional
    public void addCourse() {
        Course c = new Course();
        c.setShortTitle("SPRINGJPA");
        c.setLongTitle("Using JPA with Spring Boot");
        c.setNumberOfDays(2);
        c.setPricePerDay(425.0);
        long sizeBefore = courseService.countAllCourses();
        courseService.addCourse(c);
        long sizeAfter = courseService.countAllCourses();
        assertEquals(1,sizeAfter-sizeBefore);
    }

    @Test
    @Transactional
    public void updateCourse() {
        Course c = courseService.findCourse(7850);
        c.setLongTitle("DB2 fundamentals");
        courseService.updateCourse(c);
        assertEquals("DB2 fundamentals", courseService.findCourse(7850).getLongTitle());
    }

    @Test
    @Transactional
    public void deleteCourse8055WhichHasNoChildren() {
        long sizeBefore = courseService.countAllCourses();
        int id = 8055;
        courseService.deleteCourse(id);
        long sizeAfter = courseService.countAllCourses();
        assertEquals(1,sizeBefore-sizeAfter);
    }
}
