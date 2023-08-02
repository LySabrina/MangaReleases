package com.example.releases.services.impl;

import com.example.releases.model.User;
import com.example.releases.repository.UserRepository;
import com.example.releases.services.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    @Override
    public User getUserById(Long id) {
        Optional<User> result = userRepository.findById(id);
        return result.get();
    }


}
