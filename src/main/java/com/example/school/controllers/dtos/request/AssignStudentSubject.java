package com.example.school.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class AssignStudentSubject {

    private List<String> subjects;
}
