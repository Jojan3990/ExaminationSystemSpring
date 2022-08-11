package com.rightfindpro.become.repository;

import com.rightfindpro.become.domain.Course;
import com.rightfindpro.become.domain.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface CourseRepository extends JpaRepository<Course, Integer> {
    Optional<Course> findByName(String name);
    List<Course> findAll();

    Optional<Course> findById(Integer id);






}
