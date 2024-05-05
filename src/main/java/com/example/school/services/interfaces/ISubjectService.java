package com.example.school.services.interfaces;

import com.example.school.controllers.dtos.request.CreateSubjectRequest;
import com.example.school.controllers.dtos.response.BaseResponse;
import com.example.school.entities.Subject;


public interface ISubjectService {

    Subject findSubjectById(Long id);

    Subject getSubjectByName(String name);

    BaseResponse getAllSubjects();

    BaseResponse create(CreateSubjectRequest request);

    void delete(Long id);
}
