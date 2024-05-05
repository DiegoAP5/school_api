package com.example.school.repositories;

import com.example.school.entities.Subject;
import com.example.school.entities.projections.SubjectProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ISubjectRepository extends JpaRepository<Subject, Long> {

    @Query(value = "SELECT subjects.* FROM subjects "+
                    "WHERE subjects.name =:name",nativeQuery = true)
    Subject getSubjectByName(String name);

    @Query(value = "SELECT subjects.* FROM subjects ", nativeQuery = true)
    List<SubjectProjection> getSubjects();
}
