package com.example.sms.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
public class Payment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer paymentId;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    private Double amount;

    private LocalDateTime paymentDate;

    public Payment() {}
    public Payment(Student student, Double amount, LocalDateTime paymentDate) {
      this.student = student; this.amount = amount; this.paymentDate = paymentDate;
    }

    // getters/setters
}
