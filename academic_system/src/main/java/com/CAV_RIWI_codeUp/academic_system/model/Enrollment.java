package com.CAV_RIWI_codeUp.academic_system.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "enrollment")
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_enrollment;

    // Relationship with User (Student)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    private User user; // The enrolled student

    // Relationship with Course
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_course", referencedColumnName = "id_course")
    private Course course; // The course you are enrolling in

    @Column(nullable = false)
    private LocalDate enrollmentDate;

    /**
     * Relación con Grade (Calificación)
     * Una inscripción tiene una calificación
     */
    /*
    @OneToOne(
        mappedBy = "enrollment",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY,
        orphanRemoval = true
    )
    private Grade grade;
    */

}
