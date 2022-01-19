package com.mystory.controller;

import com.mystory.entity.CreateStudentDto;
import com.mystory.entity.Gender;
import com.mystory.entity.Student;
import com.mystory.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/students")
public class StudentController {

    private final StudentService studentService;


    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping
    public void addStudent(@RequestBody @Validated CreateStudentDto createStudentDto) {
        studentService.addStudent(createStudentDto);
    }

    @DeleteMapping("/{studentId}")
    public void deleteStudent(@PathVariable Long studentId) {
        studentService.deleteStudent(studentId);
    }

    @PutMapping("/{studentId}")
    public void updateStudent(@PathVariable Long studentId, @RequestBody Student student) throws IllegalAccessException {
        studentService.updateStudent(studentId, student);
    }
}
