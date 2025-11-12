package com.example.sms.dao;
import com.example.sms.model.Student;
import java.util.List;

public interface StudentDAO {
    Integer save(Student student);
    void update(Student student);
    void delete(Integer id);
    Student getById(Integer id);
    List<Student> getAll();
}
