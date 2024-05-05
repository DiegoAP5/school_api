package com.example.school.controllers;

import com.example.school.controllers.dtos.request.AssignStudentSubject;
import com.example.school.controllers.dtos.request.AssignStudentTutor;
import com.example.school.controllers.dtos.request.CreateStudentRequest;
import com.example.school.controllers.dtos.response.BaseResponse;
import com.example.school.services.interfaces.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("student")
public class StudentController {

    @Autowired
    private IStudentService studentService;

    @GetMapping
    public ResponseEntity<BaseResponse> getAllStudents() {
        BaseResponse baseResponse = studentService.getAllStudents();
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @GetMapping("/subject/{id}")
    public ResponseEntity<BaseResponse> getStudentById(@PathVariable Long id) {
        BaseResponse baseResponse = studentService.getStudentsSubjectsByStudentId(id);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @GetMapping("tutor/{id}")
    public ResponseEntity<BaseResponse> getStudentByTutorId(@PathVariable Long id) {
        BaseResponse baseResponse = studentService.getStudentsByTutorId(id);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody CreateStudentRequest request) {
        BaseResponse baseResponse = studentService.create(request);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PatchMapping("addTutor/{id}")
    public ResponseEntity<BaseResponse> assignTutorToStudent(@PathVariable Long id, @RequestBody AssignStudentTutor assignStudentTutor) {
        BaseResponse baseResponse = studentService.assignStudentToTutor(id, assignStudentTutor);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PatchMapping("addSubject/{id}")
    public ResponseEntity<BaseResponse> assignSubjectToStudent(@PathVariable Long id, @RequestBody AssignStudentSubject assignStudentSubject){
        BaseResponse baseResponse = studentService.assignStudentToSubject(id, assignStudentSubject);
        return new ResponseEntity<>(baseResponse,baseResponse.getHttpStatus());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){ studentService.delete(id);}
}
