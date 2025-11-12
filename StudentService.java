package com.example.sms.service;
import com.example.sms.model.Student;
import java.util.List;

public interface StudentService {
    Integer addStudent(Student s);
    void updateStudent(Student s);
    void deleteStudent(Integer id);
    Student getStudent(Integer id);
    List<Student> listStudents();
}
