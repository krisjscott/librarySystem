package org.example.Repository;

import org.example.Entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {


    User findByName(String name);
    User findByUserName(String name);
    boolean existsByUserName(String userName);
}
