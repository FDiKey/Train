package com.example.trains.Servicies;

import com.example.trains.Repo.PassengerRepo;
import com.example.trains.Repo.UserRepo;
import com.example.trains.domain.Passenger;
import com.example.trains.domain.Role;
import com.example.trains.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    final private UserRepo userRepo;

    @Autowired
    final private PassengerRepo passengerRepo;

    public UserService(UserRepo userRepo, PassengerRepo passengerRepo) {
        this.userRepo = userRepo;
        this.passengerRepo = passengerRepo;
    }

    public boolean createUser(User user)
    {
        User byName = userRepo.findByUsername(user.getUsername());
        if(byName != null)
            return false;
        user.setRoles(Collections.singleton(Role.USER));
        Passenger passenger = new Passenger();
        userRepo.save(user);
        passenger.setUser(user);
        passengerRepo.save(passenger);
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }
}
