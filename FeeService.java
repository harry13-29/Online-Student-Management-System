package com.example.sms.service;
import com.example.sms.model.Student;

public interface FeeService {
    void payFee(Integer studentId, Double amount);
    void refundFee(Integer studentId, Double amount);
}
