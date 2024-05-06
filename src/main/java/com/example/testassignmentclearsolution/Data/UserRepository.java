package com.example.testassignmentclearsolution.Data;

import com.example.testassignmentclearsolution.Entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findUsersByBirthDateBetween(LocalDate fromDate, LocalDate toDate);
}
