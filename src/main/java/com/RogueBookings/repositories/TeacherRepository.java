package com.RogueBookings.repositories;

import com.RogueBookings.models.Lesson;
import com.RogueBookings.models.Member;
import com.RogueBookings.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    boolean existsByLessonAndMember(Lesson lesson, Member member);
}