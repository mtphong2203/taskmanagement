package com.maiphong.taskmanagement.services;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maiphong.taskmanagement.entities.User;
import com.maiphong.taskmanagement.repositories.UserRepository;

@Service
@Transactional
public class AuthServiceImpl implements AuthService, UserDetailsService {
    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        // Get the user's roles
        Set<GrantedAuthority> grantedAuthorities = user.getRoles().stream()
                .map(auth -> "ROLE_" + auth.getName()) // Add the ROLE_ADMIN or ROLE_USER prefix
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                grantedAuthorities);
    }

    @Override
    public boolean existByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

}
