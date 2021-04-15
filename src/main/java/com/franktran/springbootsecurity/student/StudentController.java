package com.franktran.springbootsecurity.student;

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
        STUDENTS.add(student);
    }

    @PutMapping("/{id}")
    public void updateStudent(@PathVariable int id, @RequestBody Student student) {
        Student existStudent = getStudentById(id);
        if (Objects.nonNull(existStudent)) {
            existStudent.setName(student.getName());
            existStudent.setEmail(student.getEmail());
            int index = STUDENTS.indexOf(existStudent);
            STUDENTS.set(index, existStudent);
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
