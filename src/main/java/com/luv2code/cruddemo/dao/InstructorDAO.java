package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import com.luv2code.cruddemo.entity.Student;

import java.util.List;

public interface InstructorDAO {

    void save(Instructor theInstructor);

    Instructor findInstructorById(int theId);

    Course findCourseById(int theId);


    void deleteInstructorById(int theId);

    void deleteCourseById(int theId);

    void deleteListOfCourseById(List<Integer> ids);

    InstructorDetail findInstructorDetailById(int theId);

    void deleteInstructorDetailById(int theId);

    List<Course> findCoursesByInstructorId(int id);

    Instructor findInstructorByIdJoinFetch(int id);

    void update(Instructor tempInstructor);

    void update(Course course);

    void saveCourse(Course theCourse);

    Course findCourseAndReviewByCourseId(int id);

    Course findCourseAndStudentByCourseIdWithJoinFetch(int id);

    Student findStudentAndCourseByStudentIdWithJoinFetch(int id);

    void update(Student tempStudent);

    void deleteStudentById(int id);


}
