package RogueBookings.repositories;

import RogueBookings.models.Lesson;
import RogueBookings.models.Member;
import RogueBookings.models.Student;
import RogueBookings.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentRepository extends JpaRepository<Student, Long> {
    boolean existsByMemberAndLesson(Member member, Lesson lesson);

}