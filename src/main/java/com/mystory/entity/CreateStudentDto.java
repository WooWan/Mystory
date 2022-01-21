package com.mystory.entity;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateStudentDto {

    @NotBlank
    private String name;
    @Email
    private String email;
    @NotNull
    private Gender gender;

    public Student toEntity() {
        return Student.builder()
                .name(name)
                .email(email)
                .gender(gender)
                .build();
    }
}
