/*
package learn.jpa.demo.dao;

import learn.jpa.demo.entity.Student;

import java.util.List;

public interface StudentDao {

    Student findById(int id);

    Student findByUserName(String userName);

    // retrieve all students
    List<Student> findAll();

    // exact matching
    List<Student> findByFirstName(String firstName);

    // exact matching
    List<Student> findByLastName(String lastName);

    // reg matching
    List<Student> findByNameReg(String Name);

    // reg matching
    List<Student> findByEmailReg(String mailAddr);

    List<Integer> findIdByName(String firstName, String lastName);

    Long getNumberOfStudents();


    /**
     * Add a new unmanaged student entity into database.<br>
     * Param entity is tracked and synchronized within the transactional boundary.<br>
     * The entity should be a new, unmanaged entity instance
     * @return the tracked entity
     */
    /* Student addStudent(Student theStudent);

    /**
     * Update if id exist.<br>
     * New row with specified id schema for non-existing id.<br>
     * Entity context stay the same.
     * @return the tracked entity
     */
    /* Student saveStudent(Student theStudent);

    void updateEmail(int id, String newEmail);

    Student deleteStudent(int id);

    void deleteAllStudents();

}

     */

