package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.InstructorDAOImpl;
import com.luv2code.cruddemo.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CruddemoApplication.class, args);
    }


    @Bean
    public CommandLineRunner commandLineRunner(InstructorDAOImpl instructorDAO) {
        return runner -> {
//createCourseAndStudents(instructorDAO);
//            findCourseAndStudentRelated(instructorDAO);
//            findStudentAndCourseRelated(instructorDAO);
//            addMoreCoursesForStudent(instructorDAO);
            deleteCourse(instructorDAO);
            deleteStudent(instructorDAO);
        };
    }

    private void deleteStudent(InstructorDAOImpl instructorDAO) {
        int id = 7;
        instructorDAO.deleteStudentById(id);
    }

    private void addMoreCoursesForStudent(InstructorDAOImpl instructorDAO) {

        int id = 5;
        Student student = instructorDAO.findStudentAndCourseByStudentIdWithJoinFetch(id);

//        create more courses
        Course tempCourse1 = new Course("Rubik's Cube - How to Speed Cube");
        Course tempCourse2 = new Course("Atari 2600 - Game Development");

        //add courses to student
        student.addCourse(tempCourse1);
        student.addCourse(tempCourse2);

        System.out.println("Updating student: " + student);
        System.out.println("the associated courses: " + student.getCourses());

        instructorDAO.update(student);
        System.out.println("Done!");
    }

    private void findStudentAndCourseRelated(InstructorDAOImpl instructorDAO) {
        int id = 7;
        Student student = instructorDAO.findStudentAndCourseByStudentIdWithJoinFetch(id);
        System.out.println("the student: " + student);
        System.out.println("the associated course: " + student.getCourses());
    }

    private void findCourseAndStudentRelated(InstructorDAOImpl instructorDAO) {
        int id = 47;
        Course course = instructorDAO.findCourseAndStudentByCourseIdWithJoinFetch(id);
        System.out.println("the course: " + course);
        System.out.println("the associated student: " + course.getStudent());
    }

    private void createCourseAndStudents(InstructorDAOImpl instructorDAO) {
        //create course
        Course tempCourse = new Course("Pacman - How To Score One Million Points");
        //create student
        Student tempStudent1 = new Student("John", "Doe", "john@luv2code.com");
        Student tempStudent2 = new Student("Mary", "Public", "mary@luv2code.com");
        //add student to course
        tempCourse.addStudent(tempStudent1);
        tempCourse.addStudent(tempStudent2);
        //save course and associated students by using cascade.PERSIST
        System.out.println("saving course: " + tempCourse);
        System.out.println("associated students: " + tempCourse.getStudent());
        instructorDAO.saveCourse(tempCourse);
        System.out.println("Done!");
    }

    private void findCourseAndReview(InstructorDAOImpl instructorDAO) {
        int id = 43;

        Course course = instructorDAO.findCourseAndReviewByCourseId(id);
        System.out.println(course.getReviews());

    }

    private void createCourseAndReviews(InstructorDAOImpl instructorDAO) {
        //create course
        Course course = new Course("Pacman - How To Score One Million Points");

        //add review
        course.addReview(new Review("Greet course ... Loved it!"));
        course.addReview(new Review("Cool course, jop well done!"));
        course.addReview(new Review("What a dumb course, you are an idiot!"));

        //save the course ... and leverage cascade all
        System.out.println("saving the course");

        System.out.println(course);
        System.out.println(course.getReviews());

        instructorDAO.saveCourse(course);

        System.out.println("Done!");

    }

    private void deleteListOfCoursesById(InstructorDAOImpl instructorDAO) {
        //create list of id that need to be deleted
        List<Integer> ids = new ArrayList<>();
        ids.add(41);
        ids.add(42);
        //delete all course that has id in this list
        instructorDAO.deleteListOfCourseById(ids);
        System.out.println("Done!");
    }

    private void deleteCourse(InstructorDAOImpl instructorDAO) {
        int id = 50;

        System.out.println("deleting course id: " + id);
        instructorDAO.deleteCourseById(id);

        System.out.println("Done!");
    }

    private void updateCourse(InstructorDAOImpl instructorDAO) {
        int id = 41;

        //find instructor
        Course tempCourse = instructorDAO.findCourseById(id);

        //update some data for the instructor
        tempCourse.setTitle("Enjoy the Simple Things");

        instructorDAO.update(tempCourse);
        System.out.println("the updated instructor: " + tempCourse);
    }

    private void updateInstructor(InstructorDAOImpl instructorDAO) {
        int id = 38;

        //find instructor
        Instructor tempInstructor = instructorDAO.findInstructorById(id);

        //update some data for the instructor
        tempInstructor.setLastName("TESTER");

        instructorDAO.update(tempInstructor);
        System.out.println("the updated instructor: " + tempInstructor);
    }

    private void findInstructorWithCoursesJoinFetch(InstructorDAOImpl instructorDAO) {

        int id = 38;

        Instructor tempInstructor = instructorDAO.findInstructorByIdJoinFetch(id);
        System.out.println("the instructor: " + tempInstructor);
        //no need to associate courses with the instructor it happened automatically
        System.out.println("the courses for this instructor: " + tempInstructor.getCourses());

        System.out.println("Done!");

    }

    private void findCourseForInstructor(InstructorDAOImpl instructorDAO) {
        int id = 38;
        Instructor tempInstructor = instructorDAO.findInstructorById(id);
        System.out.println("the instructor: " + tempInstructor);
        //you need to fetch courses first from database
        List<Course> tempCourses = instructorDAO.findCoursesByInstructorId(id);
        //then associated it with instructor
        tempInstructor.setCourses(tempCourses);
        System.out.println("the courses for this instructor: " + tempInstructor.getCourses());

    }

    private void findInstructorWithCourses(InstructorDAOImpl instructorDAO) {
        int id = 38;
        System.out.println("Finding instructor id: " + id);
        Instructor tempInstructor = instructorDAO.findInstructorById(id);
        System.out.println("the instructor: " + tempInstructor);
        System.out.println("the associated courses: " + tempInstructor.getCourses());
        System.out.println("Done!");

    }

//    private void createInstructorWithCoursesWithStudent(InstructorDAOImpl instructorDAO) {
//        Instructor tempInstructor = new Instructor(
//                "Susan", "Public", "susan.public@gmail.com"
//        );
//        //create instructor detail
//        InstructorDetail tempInstructorDetail = new InstructorDetail(
//                "http://www.youtube.coom",
//                "Video Games "
//        );
//        tempInstructor.setInstructorDetail(tempInstructorDetail);
//        //create courses
//        Course tempCourse1 = new Course("Air Guitar - The Ultimate Guide");
//        Course tempCourse2 = new Course("The Pinball Masterclass");
//
//        Student tempStudent1 = new Student("ehab","Ehab@gmail.com");
//        Student tempStudent2 = new Student("moaz","moaz@gmail.com");
//        tempCourse2.add(tempStudent1);
//        tempCourse2.add(tempStudent2);
//        tempInstructor.add(tempCourse1);
//        tempInstructor.add(tempCourse2);
//        instructorDAO.save(tempInstructor);
//
//    }

    private void createInstructorWithCourses(InstructorDAOImpl instructorDAO) {
        Instructor tempInstructor = new Instructor(
                "Susan", "Public", "susan.public@gmail.com"
        );
        //create instructor detail
        InstructorDetail tempInstructorDetail = new InstructorDetail(
                "http://www.youtube.coom",
                "Video Games "
        );
        tempInstructor.setInstructorDetail(tempInstructorDetail);
        //create courses
        Course tempCourse1 = new Course("Air Guitar - The Ultimate Guide");
        Course tempCourse2 = new Course("The Pinball Masterclass");

        // associate the objects
        tempInstructor.add(tempCourse1);
        tempInstructor.add(tempCourse2);

        //saving instructor
        //
        //NOTE:this will ALSO save the courses
        //because of CascadeType.PERSIST
        //
        System.out.println("saving instructor: " + tempInstructor);
        System.out.println("The Courses " + tempInstructor.getCourses());
        instructorDAO.save(tempInstructor);

        System.out.println("Done!");
    }

    private void deleteInstructorDetail(InstructorDAOImpl instructorDAO) {

        int id = 3;
        System.out.println("deleting instructor detail: " + id);

        instructorDAO.deleteInstructorDetailById(id);

        System.out.println("Done!");
    }

    private void findInstructorDetail(InstructorDAOImpl instructorDAO) {

        int id = 2;

        InstructorDetail tempInstructorDetail = instructorDAO.findInstructorDetailById(id);

        System.out.println("the instructor details :" + tempInstructorDetail);

        System.out.println("the instructor :" + tempInstructorDetail.getInstructor());

        System.out.println("Done!");
    }

    private void deleteInstructor(InstructorDAOImpl instructorDAO) {

        int theId = 38;
        System.out.println("deleting instructor id: " + theId);

        instructorDAO.deleteInstructorById(theId);
        System.out.println("Done!");
    }

    private void findInstructor(InstructorDAOImpl instructorDAO) {

        int theId = 2;
        System.out.println("finding instructor id: " + theId);

        Instructor tempInstructor = instructorDAO.findInstructorById(theId);
        System.out.println("tempInstructor: " + tempInstructor);
        System.out.println("the associated instructorDetail only: " + tempInstructor.getInstructorDetail());
    }

    private void createInstructor(InstructorDAOImpl instructorDAO) {
/*
        // create instructor
        Instructor tempInstructor = new Instructor(
                "Chad", "Darby", "darby@gmail.com"
        );
        //create instructor detail
        InstructorDetail tempInstructorDetail = new InstructorDetail(
                "http://www.luv2code.com/youtube",
                "Luv 2 code!!!"
        );
        */
        // create instructor
        Instructor tempInstructor = new Instructor(
                "Madhu", "Patel", "madhu@gmail.com"
        );
        //create instructor detail
        InstructorDetail tempInstructorDetail = new InstructorDetail(
                "http://www.luv2code.com/youtube",
                "Guitar"
        );

        // associate the objects

        tempInstructor.setInstructorDetail(tempInstructorDetail);

        //saving instructor
        //
        //NOTE:this will ALSO save the detail object
        //because of CascadeType.ALL
        //
        System.out.println("saving instructor: " + tempInstructor);
        instructorDAO.save(tempInstructor);

        System.out.println("Done!");
    }


}
