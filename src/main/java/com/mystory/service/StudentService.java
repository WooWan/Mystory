package com.mystory.service;

import com.mystory.entity.CreateStudentDto;
import com.mystory.entity.Student;
import com.mystory.exception.BadRequestException;
import com.mystory.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class StudentService {

    private final StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Transactional
    public void addStudent(CreateStudentDto createStudentDto) {
        Student student = createStudentDto.toEntity();
//       이미 가입한 이메일이면 거부
        validateDuplicationEmail(student.getEmail());

        studentRepository.save(student);
    }

    private void validateDuplicationEmail(String email) {
        boolean isExist = studentRepository.findStudentByEmail(email)
                .isEmpty();
        if (isExist) {
            throw new BadRequestException(email + "is already signed");
        }
    }

    @Transactional
    public void deleteStudent(Long studentId) {
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, Student student) throws IllegalAccessException {
        Student findStudent = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalAccessException());
        findStudent.setEmail(student.getEmail());
        findStudent.setGender(student.getGender());
        findStudent.setName(student.getName());

    }
}
