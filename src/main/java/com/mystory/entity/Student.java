package com.mystory.entity;

import lombok.*;

import javax.persistence.*;

@ToString
@Getter @Setter
@EqualsAndHashCode
@NoArgsConstructor @AllArgsConstructor
@Builder
@Entity
public class Student {

//    @Id
//    @SequenceGenerator(
//            name = "student_sequence",
//            sequenceName= "student_sequence",
//            allocationSize = 1
//    )
//    @GeneratedValue(
//            generator = "student_sequence",
//            strategy = GenerationType.SEQUENCE)
    @Id @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    public Student(String name, String email, Gender gender) {
        this.name = name;
        this.email = email;
        this.gender = gender;
    }
}
