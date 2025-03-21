package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.InstructorDAOImpl;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CruddemoApplication.class, args);
    }


    @Bean
    public CommandLineRunner commandLineRunner(InstructorDAOImpl instructorDAO) {
        return runner -> {
//            createInstructor(instructorDAO);

//            findInstructor(instructorDAO);

            deleteInstructor(instructorDAO);
        };
    }

    private void deleteInstructor(InstructorDAOImpl instructorDAO) {

        int theId = 1;
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
