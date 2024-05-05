package com.example.school.services.interfaces;

import com.example.school.controllers.dtos.request.AssignStudentSubject;
import com.example.school.controllers.dtos.request.AssignStudentTutor;
import com.example.school.controllers.dtos.request.CreateStudentRequest;
import com.example.school.controllers.dtos.response.BaseResponse;
import com.example.school.entities.Student;

public interface IStudentService {

    Student findStudentById(Long id);

    BaseResponse getAllStudents();

    BaseResponse getStudentsSubjectsByStudentId(Long id);

    BaseResponse getStudentsByTutorId(Long id);

    BaseResponse create(CreateStudentRequest request);

    BaseResponse assignStudentToSubject(Long id, AssignStudentSubject request);

    BaseResponse assignStudentToTutor(Long id, AssignStudentTutor request);

    void delete(Long id);
}
