package com.RogueBookings.dataTransferObjects;

import com.RogueBookings.models.Lesson;
import com.RogueBookings.models.Member;
import org.springframework.stereotype.Component;

@Component
public class TeacherDTO {

    Member member;

    Lesson lesson;

    public TeacherDTO() {
    }

    public TeacherDTO(Member member, Lesson lesson) {
        this.member = member;
        this.lesson = lesson;
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
}
