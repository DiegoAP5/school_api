package com.example.school.services;

import com.example.school.controllers.dtos.request.CreateTutorRequest;
import com.example.school.controllers.dtos.response.BaseResponse;
import com.example.school.controllers.dtos.response.TutorResponse;
import com.example.school.controllers.excepcion.Excepcion;
import com.example.school.entities.Tutor;
import com.example.school.entities.projections.TutorProjection;
import com.example.school.repositories.ITutorRepository;
import com.example.school.services.interfaces.ITutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TutorServiceImpl implements ITutorService {

    @Autowired
    private ITutorRepository repository;

    @Override
    public Tutor findTutorById(Long id) {
        return repository.findById(id).orElseThrow(() -> new Excepcion("Tutor not found"));
    }

    @Override
    public BaseResponse getAllTutors() {
        List<TutorResponse> response = repository.getAllTutors().stream().map(this::from).toList();
        return BaseResponse.builder()
                .data(response)
                .success(Boolean.TRUE)
                .message("All tutors")
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse getTutorByName(String name) {
        TutorProjection response = repository.getTutorByName(name);
        return BaseResponse.builder()
                .data(response)
                .message("Tutor by name")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse create(CreateTutorRequest request) {
        Tutor tutor = new Tutor();
        tutor = create(request, tutor);
        TutorResponse response = from(repository.save(tutor));
        return BaseResponse.builder()
                .data(response)
                .message("Tutor created")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.CREATED)
                .build();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    private TutorResponse from (Tutor tutor){
        TutorResponse response = new TutorResponse();
        response.setId(tutor.getId());
        response.setFirst_name(tutor.getFirstName());
        response.setLast_name(tutor.getLastName());
        response.setEmail(tutor.getEmail());
        return response;
    }

    private TutorResponse from(TutorProjection tutor){
        TutorResponse response = new TutorResponse();
        response.setId(tutor.getId());
        response.setFirst_name(tutor.getFirst_name());
        response.setLast_name(tutor.getLast_name());
        response.setEmail(tutor.getEmail());
        return response;
    }

    private Tutor create(CreateTutorRequest request, Tutor tutor){
        tutor.setFirstName(request.getFirstName());
        tutor.setLastName(request.getLastName());
        tutor.setEmail(request.getEmail());
        return tutor;
    }
}
