package com.example.testassignmentclearsolution.Data;

import com.example.testassignmentclearsolution.Entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Override
    <S extends User> S save(S entity);

    void update(User user);

    @Override
    void delete(User user);
}
