package com.example.school.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@Entity(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String email;

    @ManyToOne
    @JoinColumn(name = "tutor_id", referencedColumnName = "id")
    private Tutor tutor;

    @ManyToMany
    @JoinTable(name = "students-subjects", joinColumns = @JoinColumn(name = "student_id"),
                inverseJoinColumns = @JoinColumn(name = "subject_id"))
    private List<Subject> subjects;
}
