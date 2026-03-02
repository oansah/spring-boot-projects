package com.prudentstar.spring_boot_demo.error;

public class EmployeeNotFoundException  extends  RuntimeException{

    public EmployeeNotFoundException(String message){
        super(message);
    }
}
