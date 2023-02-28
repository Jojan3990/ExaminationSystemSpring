package com.rightfindpro.become.exam;

//import com.rightfindpro.become.domain.Course;

import com.rightfindpro.become.exam.Exam;
import com.rightfindpro.become.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface ExamRepository extends JpaRepository<Exam, Integer> {

    //@Query("from Exam exam LEFT JOIN exam.course course where course.id = ?1")
  /*   @Query(value = "SELECT * FROM Exam exam LEFT JOIN exam.course on course.id WHERE course.id = ?1", nativeQuery = true)
    List<Exam> findExamByCourse(int course);*/

    List<Exam> findAllByCourseId(Integer courseId);

    Optional<Exam> findByName(String name);

    List<Exam> findAll();

    Optional<Exam> findById(Integer id);

    List<Exam> findAllByQuestions(Integer id);

    List<Exam> findExamByUsersId(int userId);//returns all exams related to user with input userId

    @Query(value = "FROM User e JOIN e.exams u WHERE u.id=:exam_id")
    List<User> getUsersNameByExamId(@Param("exam_id") int exam_id);

    //query to create exam guving course ID and exam name

//    @Query(value = "insert into exam(name,course_id) values (:exam_name,:course_id)",nativeQuery = true)
//    ExamDTO createExam(@Param("course_id") Integer courseId, @Param("exam_name") String examName);

//    List<Exam> findAllByUserId(Integer userId);
}
