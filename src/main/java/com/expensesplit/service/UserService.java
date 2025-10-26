package com.expensesplit.service;

import com.expensesplit.model.User;
import com.expensesplit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public User createUser(User user) {
        return userRepository.save(user);
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public User getUserById(Integer userId) {
        return userRepository.findById(userId).orElseThrow(()->new RuntimeException("User not found with id: " + userId));
    }
}
