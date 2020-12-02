package edu.server.test;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Employee
{
    @SerializedName("Emp_Name") // SerializedName giống với Key của Json
    private String emp_Name;

    public String getEmp_Name() {
        return emp_Name;
    }

    public void setEmp_Name(String emp_Name) {
        this.emp_Name = emp_Name;
    }

    public String getEmp_Gen() {
        return emp_Gen;
    }

    public void setEmp_Gen(String emp_Gen) {
        this.emp_Gen = emp_Gen;
    }

    public String getEmp_dept() {
        return emp_dept;
    }

    public void setEmp_dept(String emp_dept) {
        this.emp_dept = emp_dept;
    }

    public String getEmp_age() {
        return emp_age;
    }

    public void setEmp_age(String emp_age) {
        this.emp_age = emp_age;
    }

    public String getEmp_Pos() {
        return emp_Pos;
    }

    public void setEmp_Pos(String emp_Pos) {
        this.emp_Pos = emp_Pos;
    }

    public List<String> getEmp_colleague() {
        return emp_colleague;
    }

    public void setEmp_colleague(List<String> emp_colleague) {
        this.emp_colleague = emp_colleague;
    }

    public boolean isAgree() {
        return agree;
    }

    public void setAgree(boolean agree) {
        this.agree = agree;
    }

    @SerializedName("Emp_Gen")
    private String emp_Gen;
    @SerializedName("Emp_dept")
    private String emp_dept;
    @SerializedName("Emp_age")
    private String emp_age;
    @SerializedName("Emp_Pos")
    private String emp_Pos;
    @SerializedName("Emp_colleague")
    private List<String> emp_colleague;
    @SerializedName("agree")
    private boolean agree;
}