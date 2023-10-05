package org.jbdev.tnt.Repositories;

import jakarta.persistence.EntityManager;
import org.jbdev.tnt.entities.User;
import org.jbdev.tnt.entities.UserRole;
import org.jbdev.tnt.services.LoggedUserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{
    private final EntityManager entityManager;
    private final PasswordEncoder passwordEncoder;
    private final LoggedUserManagementService loggedUserManagementService;

    @Autowired
    public UserDaoImpl(EntityManager entityManager,
                       PasswordEncoder passwordEncoder, LoggedUserManagementService loggedUserManagementService) {
        this.entityManager = entityManager;
        this.passwordEncoder = passwordEncoder;
        this.loggedUserManagementService = loggedUserManagementService;
    }

    @Override
    @Transactional
    public void save(User user) {
        entityManager.persist(user);
        int userID = user.getUserID();
        UserRole userRole = new UserRole(userID, 2);
        entityManager.persist(userRole);
    }

    @Override
    public User userProfile() {
        String email = loggedUserManagementService.getEmail();
        List<User> users = find();
        User us = new User();
        for (User u: users) {
            if(u.getEmail().equals(email)) us = u;
        }
        return us;
    }

    @Override
    public List<User> findAllUsers() {
        return find();
    }

    @Override
    @Transactional
    public void userProfileUpdate(User user) {
        var userprofile = userProfile();
        userprofile.setLastName(user.getLastName());
        userprofile.setFirstName(user.getFirstName());
        userprofile.setPassword(passwordEncoder.encode(user.getPassword()));
        userprofile.setEmail(user.getEmail());
        entityManager.merge(userprofile);
    }

    @Override
    @Transactional
    public void userProfileDelete() {
        var user = userProfile();
        entityManager.remove(user);
    }

    @Override
    public User findUserById(int userID) {
        return entityManager.find(User.class, userID);
    }

    @Override
    @Transactional
    public void findByEmailAndUpdate(int userID, User user) {
        var u = entityManager.find(User.class, userID);
        u.setPassword(passwordEncoder.encode(user.getPassword()));
        u.setEmail(user.getEmail());
        u.setFirstName(user.getFirstName());
        u.setLastName(user.getLastName());
        entityManager.merge(u);
    }

    @Override
    @Transactional
    public void findByEmailAndDelete(int userID) {
        var user = findUserById(userID);
        entityManager.remove(user);
    }

    @Override
    public User findAdmin() {
        return entityManager.find(User.class, 1);
    }


    private List<User> find() {
        String query = "select u from User u";
        return entityManager.createQuery(query, User.class).getResultList();
    }
}
