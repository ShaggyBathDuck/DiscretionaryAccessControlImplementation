package com.bsk.services;

import com.bsk.domain.User;
import com.bsk.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

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

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(s);
        if (user == null) {
            throw new UsernameNotFoundException("Błędny login lub hasło");
        }
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), getAuthority());
    }

    private List getAuthority() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    public User findByLogin(String login){return userRepository.findByLogin(login);}

    public List<User> findByAllAttributes(String content) {
        return userRepository.findByLoginContainsOrEmailContains(content, content);
    }
}
