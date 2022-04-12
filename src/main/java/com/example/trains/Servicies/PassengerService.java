package com.example.trains.Servicies;

import com.example.trains.Repo.PassengerRepo;
import com.example.trains.domain.Passenger;
import com.example.trains.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class PassengerService {

    @Autowired
    private PassengerRepo passengerRepo;
    @Autowired
    private UserService userService;

    public void updatePassenger(User user, String email, String password, String password2, String name, String surname){
        Passenger passenger = passengerRepo.findByUser(user);
        User fromBD = userService.getUserByUsername(user.getUsername());

        boolean isEmailChanged = !fromBD.getUsername().equals(email);
        boolean isPasswordChanged = !password.isEmpty() && password.equals(password2);
        boolean isNameChanged = !passenger.getName().equals(name);
        boolean isSurnameChanged = !passenger.getSurname().equals(surname);

        if(isNameChanged || isSurnameChanged || isEmailChanged || isPasswordChanged){
            fromBD.setUsername(email);
            passenger.setName(name);
            passenger.setSurname(surname);
            if(isPasswordChanged){
                fromBD.setPassword(password);
            }

            userService.saveUser(fromBD);
            passengerRepo.save(passenger);
        }
    }

    public void getProfile(Model model, User user) {
        Passenger passenger = passengerRepo.findByUser(user);
        if(passenger != null) {
            model.addAttribute("email", user.getUsername());
            model.addAttribute("name", passenger.getName());
            model.addAttribute("surname", passenger.getSurname());
        }
    }


}
