package RogueBookings.models;

import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "lessons_students", uniqueConstraints = {
        @UniqueConstraint(name = "uc_student_member_id", columnNames = {"member_id", "lesson_id"})
})
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lessons_students_gen")
    @SequenceGenerator(name = "lessons_students_gen", sequenceName = "lessons_students_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;


    @ManyToOne(optional = false)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(optional = false)
    @JoinColumn(name = "lesson_id", nullable = false)
    private Lesson lesson;

    public Student() {
    }

    public Student(Member member, Lesson lesson) {
        this.member = member;
        this.lesson = lesson;
    }

    public Student(Long id, Member member, Lesson lesson) {
        this.id = id;
        this.member = member;
        this.lesson = lesson;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Student student = (Student) o;
        return id != null && Objects.equals(id, student.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}