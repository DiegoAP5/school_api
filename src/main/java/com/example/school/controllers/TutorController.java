package com.example.school.controllers;

import com.example.school.controllers.dtos.request.CreateTutorRequest;
import com.example.school.controllers.dtos.response.BaseResponse;
import com.example.school.entities.Tutor;
import com.example.school.services.interfaces.ITutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("tutor")
public class TutorController {

    @Autowired
    private ITutorService tutorService;

    @GetMapping
    public ResponseEntity<BaseResponse> getAllTutors() {
        BaseResponse baseResponse = tutorService.getAllTutors();
        return new ResponseEntity<>(baseResponse,baseResponse.getHttpStatus());
    }

    @GetMapping("/{name}")
    public ResponseEntity<BaseResponse> getTutorByName(@PathVariable String name){
        BaseResponse baseResponse = tutorService.getTutorByName(name);
        return new ResponseEntity<>(baseResponse,baseResponse.getHttpStatus());
    }

    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody CreateTutorRequest request){
        BaseResponse baseResponse = tutorService.create(request);
        return new ResponseEntity<>(baseResponse,baseResponse.getHttpStatus());
    }

    @DeleteMapping
    public void delete(@PathVariable Long id){tutorService.delete(id);}
}
