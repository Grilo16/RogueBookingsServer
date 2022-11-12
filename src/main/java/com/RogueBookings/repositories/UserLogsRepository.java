package com.RogueBookings.repositories;

import com.RogueBookings.models.userLogs.UserLogs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLogsRepository extends JpaRepository<UserLogs, Long> {
}