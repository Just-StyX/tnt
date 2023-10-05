package org.jbdev.tnt.Repositories;

import org.jbdev.tnt.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {
    @Query("select u from User u where u.email = :email")
    User findUserByEmail(@Param("email") String email);
}
