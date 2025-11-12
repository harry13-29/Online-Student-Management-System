package com.example.sms.model;

import jakarta.persistence.*;

@Entity
@Table(name = "courses")
public class Course {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer courseId;

    @Column(nullable = false)
    private String courseName;

    private String duration;

    public Course() {}
    public Course(String courseName, String duration) {
      this.courseName = courseName; this.duration = duration;
    }

  
}
