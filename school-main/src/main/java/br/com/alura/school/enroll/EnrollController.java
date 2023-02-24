package br.com.alura.school.enroll;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
public class EnrollController {

    @PostMapping("/{courseCode}/enroll")
    public ResponseEntity<Void> saveEnroll(@PathVariable String courseCode, @RequestBody NewEnrollRequest enrollRequest) {

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
