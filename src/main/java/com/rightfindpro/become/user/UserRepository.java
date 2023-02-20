package com.rightfindpro.become.user;

import com.rightfindpro.become.exam.Exam;
import com.rightfindpro.become.exam.ExamUserDTO;
import com.rightfindpro.become.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);

    @Query("from User  where email = :username or username = :username")
    Optional <User> getUserByEmailOrUserName(@Param("username") String username);
    User findByUsername(String username);

    List<User> findAll();

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    List<User> findUserByExamsId(int examId);//returns all users related to exam with input userId

//    void delete(Optional<User> userToDelete);

//    select user_id,count(exam_id) as exam_id from user_exam where user_id=2 GROUP BY user_id;
    @Query(value = "select count(exam_id) as exam_id from user_exam where user_id=(:user_id) GROUP BY user_id",nativeQuery = true)
    List<?> getTotalExamByUserId(@Param("user_id") int user_id);



//    @Query(value = "select name from exam where id IN (select exam_id from user_exam where user_id=(:user_id))",nativeQuery = true)
    @Query(value = "FROM Exam e JOIN e.users u WHERE u.id=:user_id")
    List<Exam> getExamsNameByUserId(@Param("user_id") int user_id);
//    List<?> getExamsNameByUserId(@Param("user_id") int user_id);

//    @Query(value = "select name from exam where id IN (select exam_id from user_exam where user_id=(:user_id))",nativeQuery = true)
//    List<?> getExamsNameByUserId(@Param("user_id") int user_id);
//    SELECT UE.name from user_exam UE WHERE UE.id IN (SELECT UE.exam_id from user_exam UE WHERE UE.user_id=(:user_id



    User findUserNameById(Integer id);


//    @Query(value = "insert into exam(name,course_id) values (:exam_name,:course_id)",nativeQuery = true)
//    ExamDTO createExam(@Param("course_id") Integer courseId, @Param("exam_name") String examName);

}
