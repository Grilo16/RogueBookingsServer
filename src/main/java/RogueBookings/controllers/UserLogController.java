package RogueBookings.controllers;


import RogueBookings.models.userLogs.UserLogs;
import RogueBookings.services.UserLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/logs")
public class UserLogController {

    UserLogService userLogService;

    @Autowired
    public UserLogController(UserLogService userLogService) {
        this.userLogService = userLogService;
    }

    @GetMapping
    public List<UserLogs> getAllUserLogs(){
        return userLogService.getAllUserLogs();
    }
}
