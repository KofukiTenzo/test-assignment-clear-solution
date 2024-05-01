package com.example.testassignmentclearsolution.Service;

import com.example.testassignmentclearsolution.DTO.AddUserDTO;
import com.example.testassignmentclearsolution.Data.UserRepository;
import com.example.testassignmentclearsolution.Entity.User;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(AddUserDTO input) {

        User user = new User();
        user.setName(input.getName());
        user.setSecond_name(input.getSecondName());
        user.setEmail(input.getEmail());
        user.setBirth_date(dateFormat(input.getBirthDate()));
        user.setAddress(input.getAddress());
        user.setPhone_number(input.getPhoneNumber());

        userRepository.save(user);
    }

    public User updateUserField(Long userId, String newEmail, String newName, String newSecondName, LocalDate newBirthDate, String newAddress, String newPhoneNumber) {

        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            return null;
        }

        if (newEmail != null) {
            user.setEmail(newEmail);
        }

        if (newName != null){
            user.setName(newName);
        }

        if (newSecondName != null){
            user.setSecond_name(newSecondName);
        }

        if (newBirthDate != null){
            user.setBirth_date(dateFormat(newBirthDate));
        }

        if (newAddress != null){
            user.setAddress(newAddress);
        }

        if (newPhoneNumber != null){
            user.setPhone_number(newPhoneNumber);
        }

        return userRepository.save(user);
    }

    public User updateAllUserFields(Long userId, String newEmail, String newName, String newSecondName, LocalDate newBirthDate, String newAddress, String newPhoneNumber) {

        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            return null;
        }

        if (newEmail == null ||
        newName == null ||
        newSecondName == null ||
        newBirthDate == null ||
        newAddress == null ||
        newPhoneNumber == null)
            return null;

        user.setEmail(newEmail);
        user.setName(newName);
        user.setSecond_name(newSecondName);
        user.setBirth_date(dateFormat(newBirthDate));
        user.setAddress(newAddress);
        user.setPhone_number(newPhoneNumber);

        return userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        userRepository.findById(userId).ifPresent(userRepository::delete);

    }

    public boolean existsById(Long userId){
        return userRepository.existsById(userId);
    }

    private String dateFormat(LocalDate inputDate) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        return inputDate.format(formatter);
    }

    public void isEighteenYO(LocalDate inputDate) {
        LocalDate legalAgeDate = LocalDate.now().minusYears(18);

        if (inputDate.isAfter(legalAgeDate))
            throw new ValidationException("User not adult!");
    }
}
