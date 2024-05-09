package com.example.testassignmentclearsolution;

import com.example.testassignmentclearsolution.DTO.AddUserDTO;
import com.example.testassignmentclearsolution.DTO.DateRangeDTO;
import com.example.testassignmentclearsolution.Data.UserRepository;
import com.example.testassignmentclearsolution.Entity.User;
import com.example.testassignmentclearsolution.Service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTests {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testAddUser() {

        AddUserDTO userInput = new AddUserDTO();
        userInput.setEmail("test@example.com");
        userInput.setName("John");
        userInput.setSecondName("Doe");
        userInput.setBirthDate(LocalDate.of(2000, 02, 12));

        userService.addUser(userInput);

        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testUpdateUserField() {

        User existingUser = new User();
        existingUser.setId(1L);
        existingUser.setEmail("john.doe@example.com");
        existingUser.setName("John");
        existingUser.setSecondName("Doe");
        existingUser.setBirthDate(LocalDate.of(1990, 5, 15));
        existingUser.setAddress("123 Main St");
        existingUser.setPhoneNumber("1234567890");

        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));

        userService.updateUserField(1L, "newEmail@example.com",
                "NewName", "NewSecondName", LocalDate.of(1995, 8, 20),
                "456 Park Ave", "0987654321");

        User updatedUser = userRepository.findById(1L).get();

        assertEquals("newEmail@example.com", updatedUser.getEmail());
        assertEquals("NewName", updatedUser.getName());
        assertEquals("NewSecondName", updatedUser.getSecondName());
        assertEquals(LocalDate.of(1995, 8, 20), updatedUser.getBirthDate());
        assertEquals("456 Park Ave", updatedUser.getAddress());
        assertEquals("0987654321", updatedUser.getPhoneNumber());

        verify(userRepository, times(1)).save(updatedUser);
    }

    @Test
    void testFindUsersByBirthdateRange() {

        LocalDate fromDate = LocalDate.of(1990, 1, 1);
        LocalDate toDate = LocalDate.of(1995, 12, 31);

        DateRangeDTO dates = new DateRangeDTO();
        dates.setFromDate(fromDate);
        dates.setToDate(toDate);

        User user1 = new User();
        user1.setId(1L);
        user1.setEmail("john.doe@example.com");
        user1.setName("John");
        user1.setSecondName("Doe");
        user1.setBirthDate(LocalDate.of(1992, 3, 15));
        user1.setAddress("123 Main St");
        user1.setPhoneNumber("1234567890");

        User user2 = new User();
        user2.setId(2L);
        user2.setEmail("jane.smith@example.com");
        user2.setName("Jane");
        user2.setSecondName("Smith");
        user2.setBirthDate(LocalDate.of(1994, 7, 20));
        user2.setAddress("456 Park Ave");
        user2.setPhoneNumber("0987654321");


        List<User> expectedUsers = Arrays.asList(user1, user2);
        when(userRepository.findUsersByBirthDateBetween(fromDate, toDate)).thenReturn(expectedUsers);

        List<User> actualUsers = userService.findUsersByBirthdateRange(dates);

        assertEquals(expectedUsers.size(), actualUsers.size());
        for (int i = 0; i < expectedUsers.size(); i++) {
            assertEquals(expectedUsers.get(i), actualUsers.get(i));
        }

        verify(userRepository, times(1)).findUsersByBirthDateBetween(fromDate, toDate);
    }
}