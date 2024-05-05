package com.example.school.repositories;

import com.example.school.entities.Tutor;
import com.example.school.entities.projections.TutorProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ITutorRepository extends JpaRepository<Tutor, Long> {

    @Query(value = "SELECT tutors.* FROM tutors "+
                    "WHERE tutors.name =:name", nativeQuery = true)
    TutorProjection getTutorByName(String name);

    @Query(value = "SELECT tutors.* FROM tutors ", nativeQuery = true)
    List<TutorProjection> getAllTutors();
}
