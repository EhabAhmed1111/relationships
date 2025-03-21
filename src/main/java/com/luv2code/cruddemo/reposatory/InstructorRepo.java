package com.luv2code.cruddemo.reposatory;

import com.luv2code.cruddemo.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

// we can use this instead, but I will go with entity manger for educational purpose
public interface InstructorRepo extends JpaRepository <Instructor,Integer> {
}
