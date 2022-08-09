package com.example.Online.shop.managment.service;

import com.example.Online.shop.managment.entity.Role;
import com.example.Online.shop.managment.entity.User;
import com.example.Online.shop.managment.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements com.example.Online.shop.managment.service.UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public User save(User user) {

        User newUser = new User(user.getName(),
                user.getEmail(), passwordEncoder.encode(user.getPassword()), Arrays.asList(new Role("ROLE_USER")));

        return userRepository.save(newUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }
        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), mapRoleAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRoleAuthorities(Collection<Role> roles) {

        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
