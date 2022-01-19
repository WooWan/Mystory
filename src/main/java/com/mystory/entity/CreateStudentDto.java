package com.mystory.entity;

import lombok.Data;

@Data
public class CreateStudentDto {

    private String name;
    private String email;
    private Gender gender;

    public Student toEntity() {
        return Student.builder()
                .name(name)
                .email(email)
                .gender(gender)
                .build();
    }
}
