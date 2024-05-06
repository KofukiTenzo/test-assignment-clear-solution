package com.example.testassignmentclearsolution.Web;

import com.example.testassignmentclearsolution.DTO.AddUserDTO;
import com.example.testassignmentclearsolution.DTO.DateRangeDTO;
import com.example.testassignmentclearsolution.DTO.UpdatingUserDTO;
import com.example.testassignmentclearsolution.Entity.User;
import com.example.testassignmentclearsolution.Service.UserService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody AddUserDTO addUserDTO) {
        try {
            userService.isEighteenYO(addUserDTO.getBirthDate());

            User user = userService.addUser(addUserDTO);

            return ResponseEntity.ok(user);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @PutMapping("update/field/{userId}")
    public ResponseEntity<?> updateUserField(@PathVariable Long userId, @RequestBody UpdatingUserDTO updatingUserDTO) {

        User updatedUser = userService.updateUserField(userId,
                updatingUserDTO.getEmail(),
                updatingUserDTO.getName(),
                updatingUserDTO.getSecondName(),
                updatingUserDTO.getBirthDate(),
                updatingUserDTO.getAddress(),
                updatingUserDTO.getPhoneNumber());

        if (updatedUser == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @PutMapping("update/all/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable Long userId, @RequestBody UpdatingUserDTO updatingUserDTO) {

        User updatedUser = userService.updateAllUserFields(userId,
                updatingUserDTO.getEmail(),
                updatingUserDTO.getName(),
                updatingUserDTO.getSecondName(),
                updatingUserDTO.getBirthDate(),
                updatingUserDTO.getAddress(),
                updatingUserDTO.getPhoneNumber());

        if (updatedUser == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {

        if (!userService.existsById(userId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        userService.deleteUser(userId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/findDateOfBirth")
    public ResponseEntity<List<User>> findUsersByBirthdateRange(@RequestBody DateRangeDTO dateRangeDTO) {

        List<User> users = userService.findUsersByBirthdateRange(dateRangeDTO.getFromDate(), dateRangeDTO.getToDate());
        return ResponseEntity.ok(users);
    }
}
