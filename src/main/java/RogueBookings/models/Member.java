package RogueBookings.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "businesses_members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "businesses_members_gen")
    @SequenceGenerator(name = "businesses_members_gen", sequenceName = "businesses_members_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "business_id", nullable = false)
    private Business business;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @JsonBackReference(value = "lessons_students")
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private Set<Student> students = new LinkedHashSet<>();

    @JsonBackReference(value = "lessons_teachers")
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private Set<Teacher> teachers = new LinkedHashSet<>();

    public Member() {
    }

    public Member(Business business, User user, Set<Student> students, Set<Teacher> teachers) {
        this.business = business;
        this.user = user;
        this.students = students;
        this.teachers = teachers;
    }

    public Member(Long id, Business business, User user, Set<Student> students, Set<Teacher> teachers) {
        this.id = id;
        this.business = business;
        this.user = user;
        this.students = students;
        this.teachers = teachers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Member that = (Member) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}