package com.example.trains.Servicies;

import com.example.trains.Repo.PassengerRepo;
import com.example.trains.Repo.UserRepo;
import com.example.trains.domain.Passenger;
import com.example.trains.domain.Role;
import com.example.trains.domain.User;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PassengerRepo passengerRepo;

    public boolean createUser(User user)
    {

        User byName = userRepo.findByUsername(user.getUsername());
        if(byName != null)
            return false;

        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());
        userRepo.save(user);
        Passenger passenger = new Passenger(userRepo.findByUsername(user.getUsername()));
        passengerRepo.save(passenger);

        if(!StringUtils.isEmpty(user.getUsername())){

        }
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepo.findByUsername(email);
    }

    public User getUserByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    public void saveUser(User user) {

        userRepo.save(user);
    }

    public Iterable<User> getAllUsers() {
        return userRepo.findAll();
    }

    public void saveUserRoles(@NotNull User user, @NotNull Map<String, String> map) {
        Set<String> roles = Arrays.stream(Role.values()).map(Role::name).collect(Collectors.toSet());

        user.getRoles().clear();
        for(String key : map.keySet()) {
            if(roles.contains(key))
                user.getRoles().add(Role.valueOf(key));
        }
        userRepo.save(user);
    }
}
