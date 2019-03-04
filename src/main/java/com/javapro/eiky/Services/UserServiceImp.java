package com.javapro.eiky.Services;

import com.javapro.eiky.Models.user.User;
import com.javapro.eiky.Models.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {
    private UserRepository userRepository;

    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User getUser(Long id) {
        return userRepository
                .findById(id)
                .orElseThrow(null);
    }

    @Override
    public User saveUser(User usr) {
        return userRepository.save(usr);
    }
    @Override
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}
