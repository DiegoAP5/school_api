package com.example.school.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateStudentRequest {

    private String firstName;

    private String lastName;

    private String email;
}
