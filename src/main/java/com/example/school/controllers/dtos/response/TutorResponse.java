package com.example.school.controllers.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TutorResponse {

    private Long id;

    private String first_name;

    private String last_name;

    private String email;
}
