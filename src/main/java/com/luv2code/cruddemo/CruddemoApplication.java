package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.InstructorDAOImpl;
import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
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

//            createInstructor(instructorDAO);
//
//            findInstructor(instructorDAO);
//
//            deleteInstructor(instructorDAO);
//
//            findInstructorDetail(instructorDAO);
//
//            deleteInstructorDetail(instructorDAO);
//
//            createInstructorWithCourses(instructorDAO);
//
//            createInstructorWithCoursesWithStudent(instructorDAO);
//
//            findInstructorWithCourses(instructorDAO);
//
//            findCourseForInstructor(instructorDAO);
//
//            findInstructorWithCoursesJoinFetch(instructorDAO);
//
//            updateInstructor(instructorDAO);
//
//            updateCourse(instructorDAO);
//
//            deleteInstructor(instructorDAO);
//
//            deleteListOfCoursesById(instructorDAO);

            deleteCourse(instructorDAO);

        };
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
        int id = 41;

        System.out.println("deleting course id: " + id);
        instructorDAO.deleteCourseById(41);

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
