package org.jbdev.tnt.services;

import org.jbdev.tnt.Repositories.UserDao;
import org.jbdev.tnt.Repositories.UserRepository;
import org.jbdev.tnt.entities.User;
import org.jbdev.tnt.securities.SecurityUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServices implements UserDetailsService {
    private final UserDao userDao;
    private final UserRepository userRepository;

    public UserServices(UserDao userDao, UserRepository userRepository) {
        this.userDao = userDao;
        this.userRepository = userRepository;
    }

    public void createUser(User user) {
        userDao.save(user);

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findUserByEmail(username);
        if(user == null) throw new UsernameNotFoundException("User not found" + username);

        return new SecurityUser(user);
    }

    public User userProfile() {
        return userDao.userProfile();
    }

    public void userProfileUpdate(User user) { userDao.userProfileUpdate(user);}
    public void userProfileDelete() {userDao.userProfileDelete(); }

    public List<User> findAll() { return userDao.findAllUsers(); }
    public User findUserById(int userID) { return userDao.findUserById(userID); }

    public void findUserAndUpdate(int userID, User user) {
        userDao.findByEmailAndUpdate(userID, user);
    }

    public void findUserAndDelete(int userID) {
        userDao.findByEmailAndDelete(userID);
    }

    public User findAdmin() { return userDao.findAdmin(); }
}
