package br.com.alura.school.enroll.services;

import br.com.alura.school.course.CourseRepository;
import br.com.alura.school.enroll.dto.NewEnrollRequest;
import br.com.alura.school.enroll.dto.response.EnrollResponse;
import br.com.alura.school.enroll.exceptions.BadRequestException;
import br.com.alura.school.enroll.exceptions.NotFoundException;
import br.com.alura.school.enroll.models.Enroll;
import br.com.alura.school.enroll.repositories.EnrollRepository;
import br.com.alura.school.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static java.lang.String.format;

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
                .orElseThrow(() -> new NotFoundException(format("Course with code %s not found.", courseCode)));

        var userFound = userRepository.findByUsername(enrollRequest.getUsername())
                .orElseThrow(() -> new NotFoundException(format("User %s not found.", enrollRequest.getUsername())));

        if (enrollRepository.existsByUserIdAndCourseId(userFound.getId(), courseFound.getId()))
            throw new BadRequestException(format("User %s already enrolled in %s course.", userFound.getUsername(), courseFound.getName()));

        //// TODO: 2/25/2023 add logs
        var enroll = new Enroll(courseFound, userFound);
        enrollRepository.save(enroll);
    }

    public List<EnrollResponse> getEnrolls() {
        return enrollRepository.findAllUsersEnrolled();
    }
}
