package com.mystory.service;

import com.mystory.entity.CreateStudentDto;
import com.mystory.entity.Gender;
import com.mystory.exception.BadRequestException;
import com.mystory.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    private StudentService studentService;
    @Mock
    private StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        studentService = new StudentService(studentRepository);
    }

    @Test
    void canGetAllAllStudents() {
        //when
        studentService.getAllStudents();
        //then
        verify(studentRepository).findAll();
    }

    @Test
    void canAddStudent() {
        //given
        CreateStudentDto student = CreateStudentDto.builder()
                .name("jamila")
                .email("jamila@gmail.com")
                .gender(Gender.FEMALE)
                .build();

        //when
        studentService.addStudent(student);
        //then

    }

    @Test
    void willThrowWhenEmailIsTaken() throws BadRequestException{
        //given
        CreateStudentDto student = CreateStudentDto.builder()
                .name("jamila")
                .email("jamila@gmail.com")
                .gender(Gender.FEMALE)
                .build();
//        //when
        given(studentRepository.existsStudentByEmail(student.getEmail()))
                .willReturn(true);
//        //then
        assertThatThrownBy(() -> studentService.addStudent(student))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("Email " + student.getEmail() + " is already signed");
    }
}