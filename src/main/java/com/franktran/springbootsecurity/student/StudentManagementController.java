package com.franktran.springbootsecurity.student;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/management/students")
public class StudentManagementController {

    private static final List<Student> STUDENTS = new ArrayList() {{
        add(new Student(1, "Frank", "frank@gmail.com"));
        add(new Student(2, "Henry", "henry@gmail.com"));
        add(new Student(3, "Bean", "bean@gmail.com"));
    }};

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

    @PostMapping
    public void createStudent(@RequestBody Student student) {
        Student lastStudent = STUDENTS.get(STUDENTS.size() - 1);
        int id = lastStudent.getId() + 1;
        student.setId(id);
        STUDENTS.add(student);
    }

    @PutMapping("/{id}")
    public void updateStudent(@PathVariable int id, @RequestBody Student student) {
        Student existStudent = getStudentById(id);
        if (Objects.nonNull(existStudent)) {
            existStudent.setName(student.getName());
            existStudent.setEmail(student.getEmail());
            int index = STUDENTS.indexOf(existStudent);
            STUDENTS.set(index, student);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable int id) {
        Student existStudent = getStudentById(id);
        if (Objects.nonNull(existStudent)) {
            STUDENTS.remove(existStudent);
        }
    }
}
