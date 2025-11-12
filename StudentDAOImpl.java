package com.example.sms.dao;

import com.example.sms.model.Student;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class StudentDAOImpl implements StudentDAO {
    private final SessionFactory sessionFactory;

    public StudentDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Integer save(Student student) {
        return (Integer) sessionFactory.getCurrentSession().save(student);
    }

    @Override
    public void update(Student student) {
        sessionFactory.getCurrentSession().update(student);
    }

    @Override
    public void delete(Integer id) {
        Student s = getById(id);
        if(s != null) sessionFactory.getCurrentSession().delete(s);
    }

    @Override
    public Student getById(Integer id) {
        return sessionFactory.getCurrentSession().get(Student.class, id);
    }

    @Override
    public List<Student> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from Student", Student.class).list();
    }
}
