package br.com.alura.school.enroll.models;

import br.com.alura.school.course.Course;
import br.com.alura.school.user.User;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.Objects;

@Entity
public class Enroll {

    @EmbeddedId
    private EnrollCompositeId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("courseId")
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    private User user;

    @Column(name = "created_on")
    private OffsetDateTime createdOn;

    public Enroll(){}

    public Enroll(Course course, User user) {
        this.course = course;
        this.user = user;
        this.id = new EnrollCompositeId(course.getId(), user.getId());
        this.createdOn = OffsetDateTime.now();
    }

    public EnrollCompositeId getId() {
        return id;
    }

    public void setId(EnrollCompositeId id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public OffsetDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(OffsetDateTime createdOn) {
        this.createdOn = createdOn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Enroll enroll = (Enroll) o;
        return Objects.equals(course, enroll.course) && Objects.equals(user, enroll.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(course, user);
    }
}
