package com.example.school.controllers.dtos.response;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class StudentSubjectReponse {

    private Long studentId;

    private String subjectId;

    private String subjectName;

    private String studentName;

    private String studentEmail;

}
