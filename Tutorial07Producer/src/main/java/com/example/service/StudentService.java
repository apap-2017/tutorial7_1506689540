package com.example.service;

import java.util.List;

import com.example.model.CourseModel;
import com.example.model.StudentModel;

public interface StudentService
{
    StudentModel selectStudent (String npm);
    
    CourseModel selectCourse (String id);
    
    List<StudentModel> selectAllStudents ();

    void addStudent (StudentModel student);

    void updateStudent (StudentModel student);
    
    void deleteStudent (String npm);
}
