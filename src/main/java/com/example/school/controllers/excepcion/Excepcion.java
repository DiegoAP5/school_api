package com.example.school.controllers.excepcion;

public class Excepcion extends  RuntimeException{

    public Excepcion(String msg){
        super(msg);
    }
}
