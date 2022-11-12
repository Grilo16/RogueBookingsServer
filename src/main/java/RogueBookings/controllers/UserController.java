package RogueBookings.controllers;


import RogueBookings.dataTransferObjects.UserDTO;
import RogueBookings.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "http://localhost:3000/")
@RequestMapping(path = "users")
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDTO> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping(value = "/{userId}")
    public UserDTO getUserById(@PathVariable Long userId){
        return userService.getUserById(userId);
    }
    @GetMapping(value = "/email/{email}")
    public UserDTO getUserById(@PathVariable String email){
        return userService.getUserByEmail(email);
    }

    @PostMapping
    public UserDTO addNewUser(@RequestBody UserDTO userDTO){
        return userService.addNewUser(userDTO);
    }

    @DeleteMapping(path = "/{userId}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long userId){
        return userService.deleteUserById(userId);
    }

    @PatchMapping(path = "/{userId}")
    public UserDTO editUser(@RequestBody UserDTO user, @PathVariable Long userId){
            return userService.editUser(user, userId);
    }

}
