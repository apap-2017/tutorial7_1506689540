package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.CourseModel;
import com.example.model.StudentModel;
import com.example.service.StudentService;

@Controller
public class StudentController
{
    @Autowired
    StudentService studentDAO;


    @RequestMapping("/")
    public String index ()
    {
        return "index";
    }


    @RequestMapping("/student/add")
    public String add ()
    {
        return "form-add";
    }


    @RequestMapping("/student/add/submit")
    public String addSubmit (
            @RequestParam(value = "npm", required = false) String npm,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "gpa", required = false) double gpa)
    {
        StudentModel student = new StudentModel (npm, name, gpa, null);
        studentDAO.addStudent (student);

        return "success-add";
    }


    @RequestMapping("/student/view")
    public String view (Model model,
            @RequestParam(value = "npm", required = false) String npm)
    {
        StudentModel student = studentDAO.selectStudent (npm);
        model.addAttribute("title", "View Student by NPM");
        if (student != null) {
            model.addAttribute ("student", student);
            return "view";
        } else {
            model.addAttribute ("npm", npm);
            return "not-found";
        }
    }


    @RequestMapping("/student/view/{npm}")
    public String viewPath (Model model,
            @PathVariable(value = "npm") String npm)
    {
        StudentModel student = studentDAO.selectStudent (npm);
        model.addAttribute("title", "View Student by NPM");
        if (student != null) {
            model.addAttribute ("student", student);
            return "view";
        } else {
            model.addAttribute ("npm", npm);
            return "not-found";
        }
    }
    
    
    @RequestMapping("/course/view/{id}")
    public String viewCourse (Model model,
            @PathVariable(value = "id") String id)
    {
        CourseModel course = studentDAO.selectCourse (id);
        model.addAttribute("title", "View Course by ID");

        if (course != null) {
            model.addAttribute ("course", course);
            return "viewcourse";
        } else {
            model.addAttribute ("id", id);
            return "not-found-course";
        }
    }


    @RequestMapping("/student/viewall")
    public String view (Model model)
    {
        List<StudentModel> students = studentDAO.selectAllStudents ();
        model.addAttribute ("students", students);
        model.addAttribute("title", "View All Students");

        return "viewall";
    }


    @RequestMapping("/student/delete/{npm}")
    public String delete (@PathVariable(value = "npm") String npm)
    {
        if (studentDAO.selectStudent(npm)==null) {
        	return "not-found";
        }
    	studentDAO.deleteStudent (npm);

        return "delete";
    }
    
    @RequestMapping("/student/update/{npm}")
    public String update(Model model, @PathVariable(value="npm") String npm) {
    	if (studentDAO.selectStudent(npm)==null) return "not-found";
    	StudentModel student = studentDAO.selectStudent(npm);
    	model.addAttribute("student", student);
    	return "form-update";
    }
    
    @RequestMapping(value="/student/update/submit", method=RequestMethod.POST)
    public String updateSubmit(@ModelAttribute StudentModel student) {
    	studentDAO.updateStudent(student);
    	return "success-update";
    }
}
