package com.example.sms.model;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studentId;

    @Column(nullable = false)
    private String name;

    private Double balance = 0.0;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id")
    private Course course;

    public Student() {}
    public Student(String name, Course course, Double balance) {
      this.name = name; this.course = course; this.balance = balance;
    }
}
