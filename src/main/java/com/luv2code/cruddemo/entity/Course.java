package com.luv2code.cruddemo.entity;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "course")
public class Course {

    // define our fields
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @ManyToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

//    @OneToMany(mappedBy = "course", cascade = {
//            CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST
//    })
//    private List<Student> students;

    public Course() {
    }

    public Course(String title) {
        this.title = title;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }

//    public List<Student> getStudent() {
//        return students;
//    }
//
//    public void setStudent(List<Student> students) {
//        this.students = students;
//    }
//    public void add(Student student){
//        if (students == null){
//            students = new ArrayList<>();
//        }
//        students.add(student);
//        student.setCourse(this);
//    }

}
