package com.mystory.repository;

import com.mystory.entity.Gender;
import com.mystory.entity.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @AfterEach
    void tearDown() {
        studentRepository.deleteAll();
    }

    @Test
    void selectExistsEmail() {
        //given
        String email = "jamila@gmail.com";
        Student student = new Student("Jamila", email, Gender.FEMALE);
        studentRepository.save(student);
        //when
        Boolean expected = studentRepository.selectExistsEmail(email);
        //then
        assertThat(expected).isTrue();
    }

    @Test
    void 미가입된_이메일() {
        //given
        String email = "jamila@gmail.com";
        Boolean expected = studentRepository.selectExistsEmail(email);
        //then
        assertThat(expected).isFalse();
    }
}