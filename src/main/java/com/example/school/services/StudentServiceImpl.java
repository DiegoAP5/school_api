package com.example.school.services;

import com.example.school.controllers.dtos.request.AssignStudentSubject;
import com.example.school.controllers.dtos.request.AssignStudentTutor;
import com.example.school.controllers.dtos.request.CreateStudentRequest;
import com.example.school.controllers.dtos.response.BaseResponse;
import com.example.school.controllers.dtos.response.StudentResponse;
import com.example.school.controllers.excepcion.Excepcion;
import com.example.school.entities.Student;
import com.example.school.entities.Subject;
import com.example.school.entities.Tutor;
import com.example.school.entities.projections.StudentProjection;
import com.example.school.repositories.IStudentRepository;
import com.example.school.services.interfaces.IStudentService;
import com.example.school.services.interfaces.ISubjectService;
import com.example.school.services.interfaces.ITutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements IStudentService {

    @Autowired
    private IStudentRepository repository;

    @Autowired
    private ISubjectService subjectService;

    @Autowired
    private ITutorService tutorService;

    @Override
    public Student findStudentById(Long id) {
        return repository.findById(id).orElseThrow(() -> new Excepcion("Student not found"));
    }

    @Override
    public BaseResponse getAllStudents() {
        List<StudentResponse> response = repository.getAllStudents().stream().map(this::from).toList();
        return BaseResponse.builder()
                .data(response)
                .message("All students found")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse getStudentsSubjectsByStudentId(Long id) {
        List<Object> response = repository.findAllSubjectsByStudentId(id).stream().toList();
        return BaseResponse.builder()
                .data(response)
                .message("Subjects by student id")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse getStudentsByTutorId(Long id){
        List<Object> response = repository.findAllStudentsByTutorId(id).stream().toList();
        return BaseResponse.builder()
                .data(response)
                .message("Students by tutor id")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse create(CreateStudentRequest request) {
        Student student = new Student();
        student = create(request, student);
        StudentResponse response = from(repository.save(student));
        return BaseResponse.builder()
                .data(response)
                .message("Student created")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.CREATED)
                .build();
    }

    @Override
    public BaseResponse assignStudentToSubject(Long id, AssignStudentSubject request) {
        Student student = findStudentById(id);
        student = assignStudentToSubject(request, student);
        StudentResponse response = from(repository.save(student));
        return BaseResponse.builder()
                .data(response)
                .message("Subjects assigned to student")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse assignStudentToTutor(Long id, AssignStudentTutor request){
        Student student = findStudentById(id);
        student = assignStudentToTutor(request, student);
        StudentResponse response = from(repository.save(student));
        return BaseResponse.builder()
                .data(response)
                .message("Tutor assigned to student")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    private StudentResponse from(Student student){
        StudentResponse response = new StudentResponse();
        response.setId(student.getId());
        response.setFirsName(student.getFirstName());
        response.setLastName(student.getLastName());
        response.setEmail(student.getEmail());
        return response;
    }

    private StudentResponse from(StudentProjection student){
        StudentResponse response = new StudentResponse();
        response.setId(student.getId());
        response.setFirsName(student.getName());
        response.setLastName(student.getSurname());
        response.setEmail(student.getEmail());
        return response;
    }

    private Student create(CreateStudentRequest request, Student student){
        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        String email = request.getEmail();
        if(email != null){
            student.setEmail(request.getEmail());
        }
        else{
            throw new Excepcion("Email is already defined");
        }
        return student;
    }

    private Student assignStudentToSubject(AssignStudentSubject request, Student student){
        List<Subject> subjects = new ArrayList<>();
        if(request.getSubjects() != null){
            for (String name: request.getSubjects()){
                Subject subject = subjectService.getSubjectByName(name);
                subjects.add(subject);
            }
            student.setSubjects(subjects);
        }
        else{
            throw new Excepcion("Subject isn't defined");
        }
        return student;
    }

    private Student assignStudentToTutor(AssignStudentTutor request, Student student){
        if(request.getTutorId() != null){
            Tutor tutor = tutorService.findTutorById(request.getTutorId());
            student.setTutor(tutor);
            return student;
        }
        else{
            throw new Excepcion("Type a valid tutor id");
        }
    }
}
