package com.example.school.controllers.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TutorResponse {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;
}
