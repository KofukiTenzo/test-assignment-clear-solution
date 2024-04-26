package com.example.testassignmentclearsolution.Service;

import com.example.testassignmentclearsolution.Data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


}
