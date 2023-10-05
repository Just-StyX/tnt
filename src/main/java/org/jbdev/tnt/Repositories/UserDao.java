package org.jbdev.tnt.Repositories;

import org.jbdev.tnt.entities.User;

import java.util.List;

public interface UserDao {
    void save(User user);
    User userProfile();
    void userProfileUpdate(User user);
    void userProfileDelete();
    User findUserById(int userID);
    List<User> findAllUsers();
    void findByEmailAndUpdate(int userID, User user);
    void findByEmailAndDelete(int userID);

    User findAdmin();
}
