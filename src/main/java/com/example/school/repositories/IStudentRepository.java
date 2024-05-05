package com.example.school.repositories;

import com.example.school.entities.Student;
import com.example.school.entities.projections.StudentProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IStudentRepository extends JpaRepository<Student, Long> {

    @Query(value = "SELECT s.name, t.name, t.email FROM subjects s JOIN students-subjects a ON s.id = a.subject_id "+
                    "JOIN students t ON t.id = a.student_id "+
                    "WHERE a.student_id =:id",nativeQuery = true)
    List<Object> findAllSubjectsByStudentId(Long id);

    @Query(value = "SELECT s.id, s.name, s.lastname, s.email FROM students s "
                    ,nativeQuery = true)
    List<StudentProjection> getAllStudents();

    @Query(value = "SELECT s.id, s.name, s.lastname, s.email FROM students s "+
                    "INNER JOIN tutors ON students.tutor_id = tutors.id "+
                    "WHERE studenst.tutor_id =:id", nativeQuery = true)
    List<Object> findAllStudentsByTutorId(Long id);
}
