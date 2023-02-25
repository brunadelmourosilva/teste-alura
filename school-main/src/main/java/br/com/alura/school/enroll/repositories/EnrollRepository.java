package br.com.alura.school.enroll.repositories;

import br.com.alura.school.enroll.models.Enroll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollRepository extends JpaRepository<Enroll, Long> {

    boolean existsByUserIdAndCourseId(Long userId, Long courseId);
}