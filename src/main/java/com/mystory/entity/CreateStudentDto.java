package com.mystory.entity;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
