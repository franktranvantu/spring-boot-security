package com.franktran.springbootsecurity.student;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public List<Student> getAllStudent() {
        return STUDENTS;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public Student getStudentById(@PathVariable int id) {
        return STUDENTS.stream()
                .filter(student -> student.getId() == id)
                .parallel()
                .findAny()
                .orElse(null);
    }

}
