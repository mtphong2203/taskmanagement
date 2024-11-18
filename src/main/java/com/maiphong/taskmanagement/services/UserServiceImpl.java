package com.maiphong.taskmanagement.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maiphong.taskmanagement.dtos.user.UserCreateDTO;
import com.maiphong.taskmanagement.dtos.user.UserDTO;
import com.maiphong.taskmanagement.dtos.user.UserEditDTO;
import com.maiphong.taskmanagement.entities.User;
import com.maiphong.taskmanagement.exceptions.ResourceNotFoundException;
import com.maiphong.taskmanagement.repositories.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserDTO> getAll() {
        List<User> users = userRepository.findAll();
        // Convert to list DTO
        List<UserDTO> userDTOs = users.stream().map(user -> {
            // do one by one
            UserDTO userDTO = new UserDTO();
            userDTO.setFirstname(user.getFirstname());
            userDTO.setLastname(user.getLastname());
            userDTO.setUsername(user.getUsername());
            userDTO.setEmail(user.getEmail());
            return userDTO;
        }).toList();
        return userDTOs;
    }

    @Override
    public UserDTO getById(UUID id) {
        User user = userRepository.findById(id).orElse(null);

        if (user == null) {
            throw new ResourceNotFoundException("User is not found");
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setFirstname(user.getFirstname());
        userDTO.setLastname(user.getLastname());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        return userDTO;
    }

    @Override
    public boolean create(UserCreateDTO userCreateDTO) {
        if (userCreateDTO == null) {
            throw new IllegalArgumentException("user create is required");
        }

        Optional<User> user = userRepository.findByUsernameOrEmail(userCreateDTO.getUsername(),
                userCreateDTO.getEmail());
        // check user is exist
        if (user.isPresent()) {
            // check username and email is already exist
            if (user.get().getEmail().equals(userCreateDTO.getEmail())
                    && user.get().getUsername().equals(userCreateDTO.getUsername())) {
                throw new IllegalArgumentException("User name and email is already exist!");
                // check if only email exist
            } else if (user.get().getEmail().equals(userCreateDTO.getEmail())) {
                throw new IllegalArgumentException("Email is already exist!");
                // check if only username exist
            } else if (user.get().getUsername().equals(userCreateDTO.getUsername())) {
                throw new IllegalArgumentException("Username is already exist!");
            }
        }

        User newUser = new User();
        newUser.setFirstname(userCreateDTO.getFirstname());
        newUser.setLastname(userCreateDTO.getLastname());
        newUser.setUsername(userCreateDTO.getUsername());
        newUser.setEmail(userCreateDTO.getEmail());
        newUser.setPassword(passwordEncoder.encode(userCreateDTO.getPassword()));

        newUser = userRepository.save(newUser);

        return newUser != null;

    }

    @Override
    public boolean update(UUID id, UserEditDTO userEditDTO) {
        if (userEditDTO == null) {
            throw new IllegalArgumentException("user create is required");
        }

        Optional<User> user = userRepository.findByUsernameOrEmail(userEditDTO.getUsername(),
                userEditDTO.getEmail());
        // check user is exist
        if (user.isPresent()) {
            // check username and email is already exist
            if (user.get().getEmail().equals(userEditDTO.getEmail())
                    && user.get().getUsername().equals(userEditDTO.getUsername())) {
                throw new IllegalArgumentException("User name and email is already exist!");
                // check if only email exist
            } else if (user.get().getEmail().equals(userEditDTO.getEmail())) {
                throw new IllegalArgumentException("Email is already exist!");
                // check if only username exist
            } else if (user.get().getUsername().equals(userEditDTO.getUsername())) {
                throw new IllegalArgumentException("Username is already exist!");
            }
        }

        User updateUser = userRepository.findById(id).orElse(null);

        if (updateUser == null) {
            throw new IllegalArgumentException("User not found");
        }

        updateUser.setFirstname(userEditDTO.getFirstname());
        updateUser.setLastname(userEditDTO.getLastname());
        updateUser.setUsername(userEditDTO.getUsername());
        updateUser.setEmail(userEditDTO.getEmail());
        updateUser.setPassword(passwordEncoder.encode(userEditDTO.getPassword()));

        updateUser = userRepository.save(updateUser);

        return updateUser != null;
    }

    @Override
    public boolean delete(UUID id) {
        User user = userRepository.findById(id).orElse(null);

        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        userRepository.delete(user);

        return !userRepository.existsById(id);

    }

}
