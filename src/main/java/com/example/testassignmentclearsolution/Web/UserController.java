package com.example.testassignmentclearsolution.Web;

import com.example.testassignmentclearsolution.DTO.AddUserDTO;
import com.example.testassignmentclearsolution.DTO.DateRangeDTO;
import com.example.testassignmentclearsolution.DTO.UpdatingUserDTO;
import com.example.testassignmentclearsolution.Entity.User;
import com.example.testassignmentclearsolution.Response.ResponseUser;
import com.example.testassignmentclearsolution.Service.UserService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/add")
    public ResponseEntity<?> addUser(@RequestBody AddUserDTO addUserDTO) {

        try {
            userService.isEighteenYO(addUserDTO.getBirthDate());

            User user = userService.addUser(addUserDTO);

//            ResponseUser responseUser = new ResponseUser();
//            responseUser.setId(user.getId());
//            responseUser.setName(user.getName());
//            responseUser.setSecondName(user.getSecondName());
//            responseUser.setEmail(user.getEmail());
//            responseUser.setBirthDate(user.getBirthDate());
//            responseUser.setAddress(user.getAddress());
//            responseUser.setPhoneNumber(user.getPhoneNumber());

            return new ResponseEntity<User>(user, HttpStatus.OK);
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

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/findDateOfBirth")
    public ResponseEntity<List<User>> findUsersByBirthdateRange(@RequestBody DateRangeDTO dateRangeDTO) {

        List<User> users = userService.findUsersByBirthdateRange(dateRangeDTO);
        return ResponseEntity.ok(users);
    }
}
