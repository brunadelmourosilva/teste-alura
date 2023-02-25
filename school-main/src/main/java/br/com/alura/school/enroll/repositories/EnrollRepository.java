package br.com.alura.school.enroll.repositories;

import br.com.alura.school.enroll.dto.response.EnrollResponse;
import br.com.alura.school.enroll.models.Enroll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollRepository extends JpaRepository<Enroll, Long> {

    boolean existsByUserIdAndCourseId(Long userId, Long courseId);

    @Query(value = "SELECT new br.com.alura.school.enroll.dto.response.EnrollResponse(e.user.email, COUNT(*) as enrollQuantity) " +
            "FROM Enroll e " +
            "JOIN e.user u " +
            "JOIN e.course c " +
            "GROUP BY e.user.email " +
            "ORDER BY enrollQuantity DESC")
    List<EnrollResponse> findAllUsersEnrolled();
}