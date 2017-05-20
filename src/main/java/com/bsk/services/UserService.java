package com.bsk.services;

import com.bsk.domain.User;
import com.bsk.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public List<User> read() {
        return userRepository.findAll();
    }

    public List<String> readUsernames(){
        return userRepository.findAll().stream().map(User::getLogin).collect(Collectors.toList());
    }

    public void delete(Integer id) {
        userRepository.delete(id);
    }

    public User findById(Integer id) {
        return userRepository.findOne(id);
    }
}
