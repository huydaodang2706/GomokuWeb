package edu.main.Test;

import java.util.Date;

public class Employee
{
    private Integer id;
    private String firstName;
    private String lastName;

    public Employee(){

    }

    public Employee(Integer id, String firstName, String lastName, Date birthDate){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    //Getters and setters

    @Override
    public String toString()
    {
        return "Employee [id=" + id + ", firstName=" + firstName + ", " +
                "lastName=" + lastName + "]";
    }
}