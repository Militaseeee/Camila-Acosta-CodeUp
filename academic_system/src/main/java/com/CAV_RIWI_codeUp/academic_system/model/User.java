package com.CAV_RIWI_codeUp.academic_system.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor // <-- Generates the empty constructor that JPA needs
@AllArgsConstructor // <-- Generates the constructor with all arguments
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_user;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String phone;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(
            mappedBy = "teacher", // teacher is the field in Course.java
            fetch = FetchType.LAZY
    )
    private List<Course> coursesAsTeacher;

    @OneToMany(
            mappedBy = "user", // user is the field in Enrollment.java
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<Enrollment> enrollmentsAsStudent;

}