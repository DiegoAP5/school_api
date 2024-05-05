package com.example.school.controllers;

import com.example.school.controllers.dtos.request.CreateSubjectRequest;
import com.example.school.controllers.dtos.response.BaseResponse;
import com.example.school.services.interfaces.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("subject")
public class SubjectController {

    @Autowired
    private ISubjectService subjectService;

    @GetMapping
    public ResponseEntity<BaseResponse> getAllSubjects() {
        BaseResponse baseResponse = subjectService.getAllSubjects();
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PostMapping
    public ResponseEntity<BaseResponse> addSubject(@RequestBody CreateSubjectRequest createSubjectRequest) {
        BaseResponse baseResponse = subjectService.create(createSubjectRequest);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @DeleteMapping("/{id}")
    public void deleteSubject(@PathVariable Long id) {subjectService.delete(id);}
}
