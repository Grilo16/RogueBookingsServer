package com.RogueBookings.repositories;

import com.RogueBookings.models.userLogs.LessonLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonLogRepository extends JpaRepository<LessonLog, Long> {
}