package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.List;

@Repository
public class InstructorDAOImpl implements InstructorDAO {

    private EntityManager entityManager;

    @Autowired
    public InstructorDAOImpl(EntityManager theEntityManger) {
        this.entityManager = theEntityManger;
    }

    @Transactional
    @Override
    public void save(Instructor theInstructor) {
        entityManager.persist(theInstructor);
    }

    @Override
    public Instructor findInstructorById(int theId) {
        //this will also return instructor detail because the default behavior
        //of one-to-one is eager mean it will fetch every thing
        return entityManager.find(Instructor.class, theId);
    }

    @Override
    public Course findCourseById(int theId) {
        return entityManager.find(Course.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int theId) {
        //find instructor
        Instructor tempInstructor = entityManager.find(Instructor.class, theId);
        //get the courses
        List<Course> courses = tempInstructor.getCourses();
        //break associations of all courses for instructor
        for (Course course : courses) {
            course.setInstructor(null);
        }
        //delete the instructor
        entityManager.remove(tempInstructor);
    }

    @Override
    @Transactional
    public void deleteCourseById(int theId) {
        //find course
        Course tempCourse = entityManager.find(Course.class, theId);

        //delete the course
        entityManager.remove(tempCourse);
    }

    @Override
    @Transactional
    public void deleteListOfCourseById(List<Integer> ids) {
        //this method just for trial
        for (int id : ids) {
            Course tempCourse = entityManager.find(Course.class, id);
            entityManager.remove(tempCourse);
        }
    }

    @Override
    public InstructorDetail findInstructorDetailById(int theId) {
        return entityManager.find(InstructorDetail.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int theId) {

        InstructorDetail tempInstructorDetail = entityManager.find(InstructorDetail.class, theId);

        //remove the associated object reference
        //break the bi-direction link
        //
        tempInstructorDetail.getInstructor().setInstructorDetail(null);


        entityManager.remove(tempInstructorDetail);

    }

    @Override
    public List<Course> findCoursesByInstructorId(int theId) {
        TypedQuery<Course> typedQuery = entityManager.createQuery(
                "from Course where instructor.id =:data", Course.class);
        typedQuery.setParameter("data", theId);
        return typedQuery.getResultList();
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int theId) {
        TypedQuery<Instructor> query = entityManager.createQuery(
                "select i from Instructor i "
                        + "JOIN FETCH i.courses "
                        + "JOIN FETCH i.instructorDetail "
                        + "where i.id = :data", Instructor.class);
        query.setParameter("data", theId);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void update(Instructor tempInstructor) {
        entityManager.merge(tempInstructor);
    }

    @Override
    @Transactional
    public void update(Course tempCourse) {
        entityManager.merge(tempCourse);
    }

    @Override
    @Transactional
    public void saveCourse(Course theCourse) {
        entityManager.persist(theCourse);
    }

    @Override
    public Course findCourseAndReviewByCourseId(int id) {
        //create the query
        TypedQuery<Course> query = entityManager.createQuery("select c from Course c "
                + "JOIN FETCH c.reviews "
                + "where c.id = :data ", Course.class
        );
        //execute the query
        query.setParameter("data", id);
        return query.getSingleResult();
    }

    @Override
    public Course findCourseAndStudentByCourseIdWithJoinFetch(int id) {

        TypedQuery<Course> query = entityManager.createQuery("select c from Course c "
                + "JOIN FETCH c.students "
                + "where c.id= :data", Course.class);
        query.setParameter("data", id);
        return query.getSingleResult();
    }

    @Override
    public Student findStudentAndCourseByStudentIdWithJoinFetch(int id) {
        TypedQuery<Student> query = entityManager.createQuery("select s from Student s "
                + "join fetch s.courses "
                + "where s.id = :data", Student.class);
        query.setParameter("data", id);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void update(Student tempStudent) {
        entityManager.merge(tempStudent);
    }

    @Override
    @Transactional
    public void deleteStudentById(int id) {

        //retrieve the student
        Student student = entityManager.find(Student.class, id);
        if (student != null) {
            //get the courses
            List<Course> courses = student.getCourses();
            //break the association
            for (Course course: courses){
                course.getStudent().remove(student);
            }
            //now you can delete student
            entityManager.remove(student);
        }
    }


}
