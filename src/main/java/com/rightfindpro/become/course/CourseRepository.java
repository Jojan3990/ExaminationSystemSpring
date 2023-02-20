package com.rightfindpro.become.course;

//import com.rightfindpro.become.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface CourseRepository extends JpaRepository<Course, Integer> {
    Optional<Course> findByName(String name);
    List<Course> findAll();

    Optional<Course> findById(Integer id);


    Optional<Course> findAllById(Integer courseId);

    @Query(value = "select count(*) as totalCourse from exam where course_id=(:courseId) GROUP BY course_id",nativeQuery = true)
    List<?> getTotalExamByCourseId(@Param("courseId") Integer courseId);
}
