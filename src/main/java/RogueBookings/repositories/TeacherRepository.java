package RogueBookings.repositories;

import RogueBookings.models.Lesson;
import RogueBookings.models.Member;
import RogueBookings.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    boolean existsByLessonAndMember(Lesson lesson, Member member);
}