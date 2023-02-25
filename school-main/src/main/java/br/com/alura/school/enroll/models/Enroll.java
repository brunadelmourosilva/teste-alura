package br.com.alura.school.enroll;

import br.com.alura.school.course.Course;
import br.com.alura.school.user.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.OffsetDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "ENROLL")
public class Enroll {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private OffsetDateTime enrollDate;

//    private Course course;
//
//    private User user;

    @Deprecated
    protected Enroll() {
        enrollDate = OffsetDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OffsetDateTime getEnrollDate() {
        return enrollDate;
    }

    public void setEnrollDate(OffsetDateTime enrollDate) {
        this.enrollDate = enrollDate;
    }
}
