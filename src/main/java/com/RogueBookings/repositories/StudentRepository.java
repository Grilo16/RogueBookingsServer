package com.RogueBookings.repositories;

import com.RogueBookings.models.Lesson;
import com.RogueBookings.models.Member;
import com.RogueBookings.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    boolean existsByMemberAndLesson(Member member, Lesson lesson);

}