package com.example.testassignmentclearsolution;


import com.example.testassignmentclearsolution.DTO.AddUserDTO;
import com.example.testassignmentclearsolution.DTO.DateRangeDTO;
import com.example.testassignmentclearsolution.DTO.UpdatingUserDTO;
import com.example.testassignmentclearsolution.Entity.User;
import com.example.testassignmentclearsolution.Service.UserService;
import com.example.testassignmentclearsolution.Web.UserController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = UserController.class)
class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @Test
    void testAddUser_SuccessSec() throws Exception {

        AddUserDTO addUserDTO = new AddUserDTO();
        addUserDTO.setEmail("test@example.com");
        addUserDTO.setName("John");
        addUserDTO.setSecondName("Doe");
        addUserDTO.setBirthDate(LocalDate.of(2000, 2, 12));

        User user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");
        user.setName("John");
        user.setSecondName("Doe");
        user.setBirthDate(LocalDate.of(2000, 2, 12));

        when(userService.addUser(any(AddUserDTO.class))).thenReturn(user);

        mockMvc.perform(post("/user/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(addUserDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.email").value("test@example.com"))
                .andExpect(jsonPath("$.name").value("John"));
    }

    @Test
    void testAddUser_Success() throws Exception {

        AddUserDTO addUserDTO = new AddUserDTO();
        addUserDTO.setEmail("test@example.com");
        addUserDTO.setName("John");
        addUserDTO.setSecondName("Doe");
        addUserDTO.setBirthDate(LocalDate.of(2000, 02, 12));

        User user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");
        user.setName("John");
        user.setSecondName("Doe");
        user.setBirthDate(LocalDate.of(2000, 02, 12));

        when(userService.addUser(any(AddUserDTO.class))).thenReturn(user);

        mockMvc.perform(post("/user/add")
                        .content(objectMapper.writeValueAsString(addUserDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(user)));
    }

    @Test
    void testUpdateUserField_Success() throws Exception {
        Long userId = 1L;

        UpdatingUserDTO updatingUserDTO = new UpdatingUserDTO();
        updatingUserDTO.setEmail("newEmail@example.com");
        updatingUserDTO.setName("NewName");

        User updatedUser = new User();
        updatedUser.setId(1L);
        updatedUser.setEmail("newEmail@example.com");
        updatedUser.setName("NewName");
        updatedUser.setSecondName("Doe");
        updatedUser.setBirthDate(LocalDate.of(2000, 02, 12));

        when(userService.updateUserField(userId, updatingUserDTO.getEmail(),
                updatingUserDTO.getName(), updatingUserDTO.getSecondName(),
                updatingUserDTO.getBirthDate(), updatingUserDTO.getAddress(),
                updatingUserDTO.getPhoneNumber())).thenReturn(updatedUser);

        mockMvc.perform(put("/user/update/field/{userId}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatingUserDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("NewName"))
                .andExpect(jsonPath("$.secondName").value("Doe"))
                .andExpect(jsonPath("$.email").value("newEmail@example.com"));
    }

    @Test
    void testUpdateAllUserFields_Success() throws Exception {

        UpdatingUserDTO updatingUserDTO = new UpdatingUserDTO();
        updatingUserDTO.setEmail("newTest@example.com");
        updatingUserDTO.setName("NewName");
        updatingUserDTO.setSecondName("NewSecName");
        updatingUserDTO.setBirthDate(LocalDate.of(1995, 02, 12));
        updatingUserDTO.setAddress("456 Park Ave");
        updatingUserDTO.setPhoneNumber("0987654321");

        User updatedUser = new User();
        updatedUser.setId(1L);
        updatedUser.setEmail("newTest@example.com");
        updatedUser.setName("NewName");
        updatedUser.setSecondName("NewSecName");
        updatedUser.setBirthDate(LocalDate.of(1995, 02, 12));
        updatedUser.setAddress("456 Park Ave");
        updatedUser.setPhoneNumber("0987654321");

        Long userId = 1L;
        when(userService.updateUserField(userId, updatingUserDTO.getEmail(),
                updatingUserDTO.getName(), updatingUserDTO.getSecondName(),
                updatingUserDTO.getBirthDate(), updatingUserDTO.getAddress(),
                updatingUserDTO.getPhoneNumber())).thenReturn(updatedUser);


        mockMvc.perform(put("/user/update/field/{userId}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatingUserDTO)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(updatedUser)));
    }

    @Test
    public void testDeleteUser_NotFound() throws Exception {

        Long userId = 1L;

        when(userService.existsById(userId)).thenReturn(false);

        mockMvc.perform(delete("/user/delete/{userId}", userId))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testFindUsersByBirthdateRange() throws Exception {

        LocalDate fromDate = LocalDate.of(1990, 1, 1);
        LocalDate toDate = LocalDate.of(2002, 1, 1);
        DateRangeDTO dateRangeDTO = new DateRangeDTO();
        dateRangeDTO.setFromDate(fromDate);
        dateRangeDTO.setToDate(toDate);

        List<User> expectedUsers = new ArrayList<>();
        User user1 = new User();
        user1.setId(1L);
        user1.setName("John");
        user1.setBirthDate(LocalDate.of(1990, 5, 15));
        expectedUsers.add(user1);
        when(userService.findUsersByBirthdateRange(any(DateRangeDTO.class))).thenReturn(expectedUsers);

        mockMvc.perform(get("/user/findDateOfBirth")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dateRangeDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("John"));
    }

}
