/*
package learn.jpa.demo.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import learn.jpa.demo.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
// indicate that a class functions as a repository
// and is supposed to communicate with the database.
// It’s a specialization of @Component, which means Spring will
// automatically detect this class for dependency injection
public class StudentDaoImpl implements StudentDao{


    private final EntityManager entityManager; // to be injected by spring

    @Autowired
    public StudentDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Student addStudent(Student theStudent) {
        entityManager.persist(theStudent);
        return theStudent;
        // the exact Student entity will be tracked by database later on
        // the entity should be a new, unmanaged entity instance (not a detached entity)
    }

    @Override
    public Student saveStudent(Student theStudent) {
        Student managedCopy = entityManager.merge(theStudent); // status of theStudent remains the same
        return managedCopy;
    }

    @Override
    public void updateEmail(int id, String newEmail) {
        String sql = "UPDATE Student SET email = :param1 WHERE id = :param2";
        Query query = entityManager.createQuery(sql);
        query.setParameter("param1", newEmail);
        query.setParameter("param2", String.valueOf(id));
        int rowsUpdated = query.executeUpdate();
        System.out.println("Number of rows updated: " + rowsUpdated);
    }

    @Override
    public Student deleteStudent(int id) {
        Student s = findById(id);
        // System.out.println("To be deleted: " + s);
        String sql = "DELETE FROM Student WHERE id = :param";
        Query query = entityManager.createQuery(sql);
        query.setParameter("param", String.valueOf(id));
        query.executeUpdate();
        return s;
    }

    @Override
    public void deleteAllStudents() {
        String sql = "DELETE FROM Student";
        Query query = entityManager.createQuery(sql);
        int rowsDeleted = query.executeUpdate();
        System.out.println("Number of rows deleted: " + rowsDeleted);
    }

    @Override
    public Student findById(int id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public Student findByUserName(String userName) {
        TypedQuery<Student> query = entityManager.createQuery("FROM Student WHERE userName = :param", Student.class);
        query.setParameter("param", userName);
        List<Student> students = query.getResultList();
        // assert a single student
        return students.get(0);
    }

    @Override
    public List<Student> findAll() {
        TypedQuery<Student> query = entityManager.createQuery("FROM Student ORDER BY lastName desc", Student.class); // JPA entity name and entity fields
        return query.getResultList(); // Execute a SELECT query and return the query results as a typed List.
    }

    @Override
    public List<Student> findByFirstName(String firstName) {
        TypedQuery<Student> query = entityManager.createQuery("FROM Student WHERE firstName = :param", Student.class);
        query.setParameter("param", firstName);
        return query.getResultList();
    }

    @Override
    public List<Student> findByLastName(String lastName) {
        TypedQuery<Student> query = entityManager.createQuery("FROM Student WHERE lastName = :param", Student.class);
        query.setParameter("param", lastName);
        return query.getResultList();
    }

    @Override
    public List<Student> findByNameReg(String Name) {
        TypedQuery<Student> query = entityManager.createQuery("FROM Student WHERE firstName LIKE :pattern OR lastName LIKE :pattern", Student.class);
        query.setParameter("pattern", "%"+Name+"%");
        return query.getResultList();
    }

    @Override
    public List<Student> findByEmailReg(String mailAddr) {
        TypedQuery<Student> query = entityManager.createQuery("FROM Student WHERE email LIKE :pattern", Student.class);
        query.setParameter("pattern", "%"+mailAddr+"%");
        return query.getResultList();
    }



    @Override
    public List<Integer> findIdByName(String firstName, String lastName) {
        String sql = "FROM Student WHERE firstName = :param1 AND lastName = :param2";
        TypedQuery<Student> query = entityManager.createQuery(sql, Student.class);
        query.setParameter("param1", firstName);
        query.setParameter("param2", lastName);
        List<Integer> Ids = new ArrayList<>();
        for (Student s : query.getResultList())
            Ids.add(s.getId());
        return Ids;
    }

    @Override
    public Long getNumberOfStudents() {
        String sql = "SELECT COUNT(s) FROM Student s";
        TypedQuery<Long> query = entityManager.createQuery(sql, Long.class);
        return query.getSingleResult();
    }


}

 */
