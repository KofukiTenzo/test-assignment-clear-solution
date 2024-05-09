package com.example.testassignmentclearsolution.Service;

import com.example.testassignmentclearsolution.DTO.AddUserDTO;
import com.example.testassignmentclearsolution.DTO.DateRangeDTO;
import com.example.testassignmentclearsolution.Data.UserRepository;
import com.example.testassignmentclearsolution.Entity.User;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(AddUserDTO input) {

        User user = new User();
        user.setName(input.getName());
        user.setSecondName(input.getSecondName());
        user.setEmail(input.getEmail());
        user.setBirthDate(input.getBirthDate());
        user.setAddress(input.getAddress());
        user.setPhoneNumber(input.getPhoneNumber());

        return userRepository.save(user);
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
            user.setSecondName(newSecondName);
        }

        if (newBirthDate != null){
            user.setBirthDate(newBirthDate);
        }

        if (newAddress != null){
            user.setAddress(newAddress);
        }

        if (newPhoneNumber != null){
            user.setPhoneNumber(newPhoneNumber);
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
        user.setSecondName(newSecondName);
        user.setBirthDate(newBirthDate);
        user.setAddress(newAddress);
        user.setPhoneNumber(newPhoneNumber);

        return userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        userRepository.findById(userId).ifPresent(userRepository::delete);

    }

    public List<User> findUsersByBirthdateRange(DateRangeDTO dates) {
        if (dates.getFromDate().isAfter(dates.getToDate())) {
            throw new IllegalArgumentException("Date 'From' must be before date 'To'");
        }
        return userRepository.findUsersByBirthDateBetween(dates.getFromDate(), dates.getToDate());
    }

    public boolean existsById(Long userId){
        return userRepository.existsById(userId);
    }

    public void isEighteenYO(LocalDate inputDate) {
        LocalDate legalAgeDate = LocalDate.now().minusYears(18);

        if (inputDate.isAfter(legalAgeDate))
            throw new ValidationException("User not adult!");
    }
}
