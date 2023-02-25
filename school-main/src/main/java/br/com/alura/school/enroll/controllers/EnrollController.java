package br.com.alura.school.enroll.controllers;

import br.com.alura.school.course.Course;
import br.com.alura.school.course.CourseRepository;
import br.com.alura.school.enroll.NewEnrollRequest;
import br.com.alura.school.enroll.services.EnrollService;
import br.com.alura.school.user.User;
import br.com.alura.school.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
public class EnrollController {

    @Autowired
    private EnrollService enrollService;


    @PostMapping("/{courseCode}/enroll")
    public ResponseEntity<Void> saveEnroll(@PathVariable String courseCode, @RequestBody NewEnrollRequest enrollRequest) {
        enrollService.saveEnroll(courseCode, enrollRequest);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
