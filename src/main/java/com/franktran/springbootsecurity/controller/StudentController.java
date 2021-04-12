package com.franktran.springbootsecurity.controller;

import com.franktran.springbootsecurity.model.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private static final List<Student> STUDENTS = Arrays.asList(
            new Student(1, "Frank", "frank@gmail.com"),
            new Student(2, "Henry", "henry@gmail.com"),
            new Student(3, "Bea", "bean@gmail.com")
    );

    @GetMapping("/public")
    public String publicResources() {
        return "This is public resources";
    }

    @GetMapping
    public List<Student> getAllStudent() {
        return STUDENTS;
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable int id) {
        return STUDENTS.stream()
                .filter(student -> student.getId() == id)
                .parallel()
                .findAny()
                .orElse(null);
    }
}
