package RogueBookings.repositories;

import RogueBookings.models.userLogs.UserLogs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLogsRepository extends JpaRepository<UserLogs, Long> {
}