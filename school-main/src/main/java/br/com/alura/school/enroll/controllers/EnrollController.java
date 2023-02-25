package br.com.alura.school.enroll.controllers;

import br.com.alura.school.course.Course;
import br.com.alura.school.course.CourseRepository;
import br.com.alura.school.enroll.NewEnrollRequest;
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
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    //// TODO: 2/24/2023  verificar se usuário consegue se cadastrar no mesmo curso
    @PostMapping("/{courseCode}/enroll")
    public ResponseEntity<Void> saveEnroll(@PathVariable String courseCode, @RequestBody NewEnrollRequest enrollRequest) {
        Course course = new Course("11", "course1", "2222222222");
        courseRepository.save(course);

        User user = new User("bruna", "bruna@email.com");
        user.addCourse(course);

        userRepository.save(user);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
