package br.com.alura.school.course;

import br.com.alura.school.enroll.models.Enroll;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Size(max=10)
    @NotBlank
    @Column(nullable = false, unique = true)
    private String code;

    @Size(max=20)
    @NotBlank
    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Enroll> users = new ArrayList<>();

    @Deprecated
    protected Course() { }

    public Course(String code, String name, String description) {
        this.code = code;
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    String getDescription() {
        return description;
    }

    public List<Enroll> getUsers() {
        return users;
    }

    public void setUsers(List<Enroll> users) {
        this.users = users;
    }
}
