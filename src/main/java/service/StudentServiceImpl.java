package service;

import entity.Student;

import javax.persistence.*;
import java.util.List;

public class StudentServiceImpl implements StudentService {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("studentPU");

    @Override
    public void save(Student student) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(student);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void update(Student student) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(student);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void delete(Student student) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Student merged = em.merge(student);
        em.remove(merged);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<Student> findAll() {
        EntityManager em = emf.createEntityManager();
        List<Student> list = em.createQuery("SELECT s FROM Student s", Student.class).getResultList();
        em.close();
        return list;
    }
}
