package com.example.sms.service;
import com.example.sms.dao.StudentDAO;
import com.example.sms.dao.PaymentDAO;
import com.example.sms.model.Student;
import com.example.sms.model.Payment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class FeeServiceImpl implements FeeService {
    private final StudentDAO studentDAO;
    private final PaymentDAO paymentDAO;

    public FeeServiceImpl(StudentDAO studentDAO, PaymentDAO paymentDAO) {
        this.studentDAO = studentDAO;
        this.paymentDAO = paymentDAO;
    }

    @Override
    @Transactional
    public void payFee(Integer studentId, Double amount) {
        Student s = studentDAO.getById(studentId);
        if (s == null) throw new IllegalArgumentException("Student not found: " + studentId);

        // Example of multi-step: update student balance and create payment record
        s.setBalance(s.getBalance() + amount);
        studentDAO.update(s);

        Payment p = new Payment(s, amount, LocalDateTime.now());
        paymentDAO.save(p);

        // If any exception thrown here, transaction will rollback
    }

    @Override
    @Transactional
    public void refundFee(Integer studentId, Double amount) {
        Student s = studentDAO.getById(studentId);
        if (s == null) throw new IllegalArgumentException("Student not found: " + studentId);
        if (s.getBalance() < amount) throw new IllegalArgumentException("Insufficient balance to refund");

        s.setBalance(s.getBalance() - amount);
        studentDAO.update(s);

        // Save negative payment or a refund record (simple approach below)
        Payment p = new Payment(s, -amount, LocalDateTime.now());
        paymentDAO.save(p);
    }
}
