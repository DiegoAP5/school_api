package com.example.school.services.interfaces;

import com.example.school.controllers.dtos.request.CreateTutorRequest;
import com.example.school.controllers.dtos.response.BaseResponse;
import com.example.school.entities.Tutor;

public interface ITutorService {

    Tutor findTutorById(Long id);

    BaseResponse getAllTutors();

    BaseResponse getTutorByName(String name);

    BaseResponse create(CreateTutorRequest request);

    void delete(Long id);
}
