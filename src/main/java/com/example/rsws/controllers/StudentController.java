package com.example.rsws.controllers;


import com.example.rsws.exceptions.StudentNotFoundException;
import com.example.rsws.models.Student;
import com.example.rsws.services.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/")
@Api(tags = "Student Queries", value = "Student")
public class StudentController {

    Logger logger = LoggerFactory.getLogger(StudentController.class);

    private static final String STUDENT_WITH = "Student with ";
    private static final String IS_NOT_FOUND = " is Not Found!";
    StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @ApiOperation(value = "View a list of available Students",response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @GetMapping(value = "/students", produces = {"application/json;charset=utf-8"})
    public List<Student> getAllStudents() {
        logger.info("Getting all Students List");
        return studentService.getAllStudents();
    }

    @ApiOperation(value = "Search a Student with an ID",response = Student.class)
    @GetMapping(value = "/students/{id}", produces = {"application/json;charset=utf-8"})
    public Student getStudentById(@PathVariable("id") @Min(1) int id) {
        logger.info("Getting student information based on id: "+id);
        return studentService.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(STUDENT_WITH + id + IS_NOT_FOUND));
    }

    @ApiOperation(value = "Add a Student")
    @PostMapping(value = "/students", produces = {"application/json;charset=utf-8"})
    public Student addStudent(@Valid @RequestBody Student std) {
        logger.info("Adding student information in database");
        return studentService.save(std);
    }

    @ApiOperation(value = "Update a Student")
    @PutMapping(value = "/students/{id}", produces = {"application/json;charset=utf-8"})
    public Student updateStudent(@PathVariable("id") @Min(1) int id, @Valid @RequestBody Student newstd) {
        logger.info("Updating student info based on given id: "+id);
        Student stdu = studentService.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(STUDENT_WITH + id + IS_NOT_FOUND));
        stdu.setFirstname(newstd.getFirstname());
        stdu.setLastname(newstd.getLastname());
        stdu.setEmail(newstd.getEmail());
        stdu.setEmail(newstd.getPhone());
        return studentService.save(stdu);
    }

    @ApiOperation(value = "Delete a Student")
    @DeleteMapping(value = "/students/{id}", produces = {"application/json;charset=utf-8"})
    public String deleteStudent(@PathVariable("id") @Min(1) int id) {
        logger.info("Deleting student info from database by given student id: "+id);
        Student std = studentService.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(STUDENT_WITH + id + IS_NOT_FOUND));
        studentService.deleteById(std.getId());
        return "Student with ID :" + id + " is deleted";
    }
}