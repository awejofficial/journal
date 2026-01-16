package com.awej.journal.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.awej.journal.entity.User;
import com.awej.journal.repository.UserRepository;

@Component
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public void saveEntry(User user) {
        userRepository.save(user);
    }

    public List<User> getAll() {
        // This will now correctly return List<User>
        return userRepository.findAll();
    }

    public Optional<User> findById(ObjectId id) {
        // This will now correctly return Optional<User>
        return userRepository.findById(id);
    }

    public void deleteById(ObjectId id) {
        userRepository.deleteById(id);
    }

    public User findByUserName(User userName) {
        return userRepository.findByUserName(userName);
    }

    public User findByUserName(String userName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByUserName'");
    }
}