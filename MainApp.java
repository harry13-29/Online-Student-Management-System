package com.example.sms;

import com.example.sms.config.AppConfig;
import com.example.sms.model.Course;
import com.example.sms.model.Student;
import com.example.sms.service.CourseService;
import com.example.sms.service.FeeService;
import com.example.sms.service.StudentService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class)) {
            StudentService studentService = ctx.getBean(StudentService.class);
            CourseService courseService = ctx.getBean(CourseService.class);
            FeeService feeService = ctx.getBean(FeeService.class);

            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.println("\n--- Student Management ---");
                System.out.println("1. Add Course");
                System.out.println("2. Add Student");
                System.out.println("3. List Students");
                System.out.println("4. Pay Fee");
                System.out.println("5. Refund Fee");
                System.out.println("6. Exit");
                System.out.print("Choose: ");
                int c = Integer.parseInt(sc.nextLine());

                switch (c) {
                    case 1 -> {
                        System.out.print("Course name: ");
                        String name = sc.nextLine();
                        System.out.print("Duration: ");
                        String dur = sc.nextLine();
                        Course course = new Course(name, dur);
                        courseService.addCourse(course);
                        System.out.println("Course added: " + course);
                    }
                    case 2 -> {
                        System.out.print("Student name: ");
                        String sname = sc.nextLine();
                        System.out.print("Course ID: ");
                        Integer cid = Integer.parseInt(sc.nextLine());
                        Course course = courseService.getCourse(cid);
                        if (course == null) {
                            System.out.println("Course not found");
                            break;
                        }
                        Student student = new Student(sname, course, 0.0);
                        Integer id = studentService.addStudent(student);
                        System.out.println("Student added with id " + id);
                    }
                    case 3 -> {
                        List<Student> students = studentService.listStudents();
                        students.forEach(System.out::println);
                    }
                    case 4 -> {
                        System.out.print("Student ID: ");
                        Integer sid = Integer.parseInt(sc.nextLine());
                        System.out.print("Amount: ");
                        Double amt = Double.parseDouble(sc.nextLine());
                        try {
                            feeService.payFee(sid, amt);
                            System.out.println("Payment successful");
                        } catch (Exception ex) {
                            System.out.println("Payment failed: " + ex.getMessage());
                        }
                    }
                    case 5 -> {
                        System.out.print("Student ID: ");
                        Integer sid = Integer.parseInt(sc.nextLine());
                        System.out.print("Amount: ");
                        Double amt = Double.parseDouble(sc.nextLine());
                        try {
                            feeService.refundFee(sid, amt);
                            System.out.println("Refund successful");
                        } catch (Exception ex) {
                            System.out.println("Refund failed: " + ex.getMessage());
                        }
                    }
                    case 6 -> {
                        System.out.println("Bye");
                        return;
                    }
                    default -> System.out.println("Invalid choice");
                }
            }
        }
    }
}
