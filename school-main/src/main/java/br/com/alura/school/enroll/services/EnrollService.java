package br.com.alura.school.enroll.services;

import br.com.alura.school.course.CourseRepository;
import br.com.alura.school.enroll.NewEnrollRequest;
import br.com.alura.school.enroll.models.Enroll;
import br.com.alura.school.enroll.repositories.EnrollRepository;
import br.com.alura.school.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class EnrollService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EnrollRepository enrollRepository;

    public void saveEnroll(String courseCode, NewEnrollRequest enrollRequest) {

        var courseFound = courseRepository.findByCode(courseCode)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, format("Course with code %s not found", courseCode)));

        var userFound = userRepository.findByUsername(enrollRequest.getUsername())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, format("User %s not found", enrollRequest.getUsername())));
        
        
        if (enrollRepository.existsByUserIdAndCourseId(userFound.getId(), courseFound.getId()))
            throw new RuntimeException(); //// TODO: 2/25/2023 replace to bad request 

        var enroll = new Enroll(courseFound, userFound);
        enrollRepository.save(enroll);
    }
}
