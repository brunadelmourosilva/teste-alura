package br.com.alura.school.enroll.services;

import br.com.alura.school.course.Course;
import br.com.alura.school.course.CourseRepository;
import br.com.alura.school.enroll.dto.request.NewEnrollRequest;
import br.com.alura.school.enroll.dto.response.EnrollResponse;
import br.com.alura.school.enroll.exceptions.BadRequestException;
import br.com.alura.school.enroll.exceptions.NotFoundException;
import br.com.alura.school.enroll.models.Enroll;
import br.com.alura.school.enroll.repositories.EnrollRepository;
import br.com.alura.school.user.User;
import br.com.alura.school.user.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EnrollServiceTest {

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private EnrollRepository enrollRepository;

    @InjectMocks
    private EnrollService enrollService;

    @Test
    void should_save_new_enroll() {
        var courseCode = "java-1";
        var userCode = "bruna_delmouro";

        var course = new Course(courseCode, "Kafka e Spring Boot", "");
        var user = new User(userCode, "bruna@email.com");
        var newEnrollRequest = new NewEnrollRequest("bruna_delmouro");

        when(courseRepository.findByCode(courseCode)).thenReturn(Optional.of(course));

        when(userRepository.findByUsername(userCode)).thenReturn(Optional.of(user));

        when(enrollRepository.existsByUserIdAndCourseId(user.getId(), course.getId())).thenReturn(false);

        enrollService.saveEnroll(courseCode, newEnrollRequest);

        verify(enrollRepository).save(new Enroll(course, user));
    }

    @Test
    void should_throws_a_not_found_exception_when_courseId_not_found() {
        var courseCode = RandomStringUtils.randomAlphabetic(4);
        var userCode = "bruna_delmouro";

        var newEnrollRequest = new NewEnrollRequest(userCode);

        when(courseRepository.findByCode(courseCode)).thenReturn(Optional.empty());

        var exception = assertThrows(
                NotFoundException.class,
                () ->  enrollService.saveEnroll(courseCode, newEnrollRequest)
        );

        assertEquals("Course with code " + courseCode + " not found.", exception.getMessage());
    }

    @Test
    void should_throw_a_not_found_exception_when_userId_not_found() {
        var courseCode = "java-1";
        var userCode = RandomStringUtils.randomAlphabetic(4);

        var newEnrollRequest = new NewEnrollRequest(userCode);
        var course = new Course(courseCode, "Java e Kafka", "");

        when(courseRepository.findByCode(courseCode)).thenReturn(Optional.of(course));

        when(userRepository.findByUsername(userCode)).thenReturn(Optional.empty());

        var exception = assertThrows(
                NotFoundException.class,
                () ->  enrollService.saveEnroll(courseCode, newEnrollRequest)
        );

        assertEquals("User " + userCode + " not found.", exception.getMessage());
    }

    @Test
    void should_throw_a_bad_request_exception_when_userId_and_courseId_already_exist() {
        var courseCode = "java-1";
        var userCode = "bruna_delmouro";

        var newEnrollRequest = new NewEnrollRequest(userCode);
        var course = new Course(courseCode, "Java e Kafka", "");
        var user = new User(userCode, "bruna@email.com");

        when(courseRepository.findByCode(courseCode)).thenReturn(Optional.of(course));

        when(userRepository.findByUsername(userCode)).thenReturn(Optional.of(user));

        when(enrollRepository.existsByUserIdAndCourseId(user.getId(), course.getId())).thenReturn(true);

        var exception = assertThrows(
                BadRequestException.class,
                () ->  enrollService.saveEnroll(courseCode, newEnrollRequest)
        );

        assertEquals("User bruna_delmouro already enrolled in Java e Kafka course.", exception.getMessage());
    }

    @Test
    void should_get_all_enrolls() {
        var enroll = new EnrollResponse("bruna@email.com", 2L);

        when(enrollRepository.findAllUsersEnrolled()).thenReturn(List.of(enroll));

        var response = enrollService.getEnrolls();

        assertEquals("bruna@email.com", response.get(0).getEmail());
        assertEquals(2L, response.get(0).getEnrollQuantity());
    }
}
