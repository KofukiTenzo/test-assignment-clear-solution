package com.example.testassignmentclearsolution.Data;

import com.example.testassignmentclearsolution.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User save(User user);

    List<User> findUsersByBirthDateBetween(LocalDate fromDate, LocalDate toDate);
}
