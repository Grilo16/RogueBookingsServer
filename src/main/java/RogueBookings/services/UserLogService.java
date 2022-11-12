package RogueBookings.services;

import RogueBookings.models.userLogs.UserLogs;
import RogueBookings.repositories.UserLogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserLogService {
    UserLogsRepository userLogsRepository;

    @Autowired
    public UserLogService(UserLogsRepository userLogsRepository) {
        this.userLogsRepository = userLogsRepository;
    }

    public List<UserLogs> getAllUserLogs() {
        return userLogsRepository.findAll();
    }
}
