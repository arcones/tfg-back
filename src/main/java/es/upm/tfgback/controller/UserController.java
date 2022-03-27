package es.upm.tfgback.controller;

import es.upm.tfgback.model.User;
import es.upm.tfgback.model.TFG;
import es.upm.tfgback.repository.UserRepository;
import es.upm.tfgback.repository.TFGRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    final UserRepository userRepository;
    final TFGRepository tfgRepository;

    public UserController(UserRepository userRepository, TFGRepository tfgRepository) {
        this.userRepository = userRepository;
        this.tfgRepository = tfgRepository;
    }

    @CrossOrigin
    @GetMapping("/users/login")
    public ResponseEntity<User> login(@RequestHeader("Mail") String mail, @RequestHeader("Password") String password) {
        try {
            Optional<User> userData = userRepository.findByMail(mail);
            if (userData.isPresent() && Objects.equals(userData.get().getPassword(), password)) {
                return new ResponseEntity<>(userData.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}