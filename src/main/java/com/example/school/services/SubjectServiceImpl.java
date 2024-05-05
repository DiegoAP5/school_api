package com.example.school.services;

import com.example.school.controllers.dtos.request.CreateSubjectRequest;
import com.example.school.controllers.dtos.response.BaseResponse;
import com.example.school.controllers.dtos.response.SubjectResponse;
import com.example.school.controllers.excepcion.Excepcion;
import com.example.school.entities.Subject;
import com.example.school.entities.projections.SubjectProjection;
import com.example.school.repositories.ISubjectRepository;
import com.example.school.services.interfaces.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements ISubjectService {

    @Autowired
    private ISubjectRepository repository;

    @Override
    public Subject findSubjectById(Long id) {
        return repository.findById(id).orElseThrow(() -> new Excepcion("Subject not found"));
    }

    @Override
    public Subject getSubjectByName(String name) {
        return repository.getSubjectByName(name);
    }

    @Override
    public BaseResponse getAllSubjects() {
        List<SubjectResponse> response = repository.getSubjects().stream().map(this::from).toList();
        return BaseResponse.builder()
                .data(response)
                .message("All subjects")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse create(CreateSubjectRequest request) {
        Subject subject = new Subject();
        subject = create(request, subject);
        SubjectResponse response = from(repository.save(subject));
        return BaseResponse.builder()
                .data(response)
                .message("Subject created")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.CREATED)
                .build();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    private SubjectResponse from(Subject subject) {
        SubjectResponse response = new SubjectResponse();
        response.setId(subject.getId());
        response.setName(subject.getName());
        return response;
    }

    private SubjectResponse from(SubjectProjection subject) {
        SubjectResponse response = new SubjectResponse();
        response.setId(subject.getId());
        response.setName(subject.getName());
        return response;
    }

    private Subject create(CreateSubjectRequest request, Subject subject){
        subject.setName(request.getName());
        return subject;
    }
}
