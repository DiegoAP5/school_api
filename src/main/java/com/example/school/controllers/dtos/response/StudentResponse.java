package com.example.school.controllers.dtos.response;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class StudentResponse {

    private Long id;

    private String firsName;

    private String lastName;

    private String email;
}
