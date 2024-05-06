package com.example.testassignmentclearsolution;

import com.example.testassignmentclearsolution.DTO.AddUserDTO;
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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTests {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testAddUser() {
        // Given
        AddUserDTO userInput = new AddUserDTO("John", "Doe", "john.doe@example.com",
                LocalDate.of(1990, 5, 15), "123 Main St", "1234567890");

        // When
        userService.addUser(userInput);

        // Then
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testUpdateUserField() {
        // Given
        User existingUser = new User(1L, "John", "Doe", "john.doe@example.com",
                LocalDate.of(1990, 5, 15), "123 Main St", "1234567890");
        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));

        // When
        User updatedUser = userService.updateUserField(1L, "newEmail@example.com",
                "NewName", "NewSecondName", LocalDate.of(1995, 8, 20),
                "456 Park Ave", "0987654321");

        // Then
        assertNotNull(updatedUser);
        assertEquals("newEmail@example.com", updatedUser.getEmail());
        assertEquals("NewName", updatedUser.getName());
        assertEquals("NewSecondName", updatedUser.getSecondName());
        assertEquals(LocalDate.of(1995, 8, 20), updatedUser.getBirthDate());
        assertEquals("456 Park Ave", updatedUser.getAddress());
        assertEquals("0987654321", updatedUser.getPhoneNumber());
        verify(userRepository, times(1)).save(existingUser);
    }

    @Test
    void testFindUsersByBirthdateRange() {
        // Given
        LocalDate fromDate = LocalDate.of(1990, 1, 1);
        LocalDate toDate = LocalDate.of(1995, 12, 31);
        User user1 = new User(1L, "John", "Doe", "john.doe@example.com", LocalDate.of(1992, 3, 15),
                "123 Main St", "1234567890");
        User user2 = new User(2L, "Jane", "Smith", "jane.smith@example.com", LocalDate.of(1994, 7, 20),
                "456 Park Ave", "0987654321");
        List<User> expectedUsers = Arrays.asList(user1, user2);
        when(userRepository.findUsersByBirthDateBetween(fromDate, toDate)).thenReturn(expectedUsers);

        // When
        List<User> actualUsers = userService.findUsersByBirthdateRange(fromDate, toDate);

        // Then
        assertEquals(expectedUsers.size(), actualUsers.size());
        for (int i = 0; i < expectedUsers.size(); i++) {
            assertEquals(expectedUsers.get(i), actualUsers.get(i));
        }
        verify(userRepository, times(1)).findUsersByBirthDateBetween(fromDate, toDate);
    }
}