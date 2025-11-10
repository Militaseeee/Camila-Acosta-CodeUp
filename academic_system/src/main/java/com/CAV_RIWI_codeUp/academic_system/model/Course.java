package com.CAV_RIWI_codeUp.academic_system.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_teacher", referencedColumnName = "id_user")
    private User teacher;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String credits;

    // Relationship with Enrollment -> A course can have multiple enrollments.
    @OneToMany(
        mappedBy = "course",
        cascade = CascadeType.ALL,
        orphanRemoval = true,
        fetch = FetchType.LAZY
    )
    private List<Enrollment> enrollments;

}
