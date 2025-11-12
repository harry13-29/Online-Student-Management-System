package com.example.sms.service;
import com.example.sms.dao.StudentDAO;
import com.example.sms.model.Student;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentDAO studentDAO;

    public StudentServiceImpl(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    @Override
    @Transactional
    public Integer addStudent(Student s) {
        return studentDAO.save(s);
    }

    @Override
    @Transactional
    public void updateStudent(Student s) {
        studentDAO.update(s);
    }

    @Override
    @Transactional
    public void deleteStudent(Integer id) {
        studentDAO.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Student getStudent(Integer id) {
        return studentDAO.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Student> listStudents() {
        return studentDAO.getAll();
    }
}
