package edu.server.Test;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Employee
{
    @SerializedName("Emp_Name") // SerializedName giống với Key của Json
    public String emp_Name;
    @SerializedName("Emp_Gen")
    public String emp_Gen;
    @SerializedName("Emp_dept")
    public String emp_dept;
    @SerializedName("Emp_age")
    public String emp_age;
    @SerializedName("Emp_Pos")
    public String emp_Pos;
    @SerializedName("Emp_colleague")
    public List<String> emp_colleague;
}