package br.com.alura.school.enroll.controllers;

import br.com.alura.school.enroll.dto.request.NewEnrollRequest;
import br.com.alura.school.enroll.dto.response.EnrollResponse;
import br.com.alura.school.enroll.services.EnrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/courses")
public class EnrollController {

    @Autowired
    private EnrollService enrollService;


    @PostMapping("/{courseCode}/enroll")
    public ResponseEntity<Void> saveEnroll(@PathVariable String courseCode, @Valid @RequestBody NewEnrollRequest enrollRequest) {
        enrollService.saveEnroll(courseCode, enrollRequest);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/enroll/report")
    public ResponseEntity<List<EnrollResponse>> getEnrolls() {
        var enrollResponse = enrollService.getEnrolls();

        return enrollResponse.isEmpty() ?
                    ResponseEntity.noContent().build() :
                    ResponseEntity.ok(enrollResponse);
    }
}
