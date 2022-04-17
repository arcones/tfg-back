package es.upm.tfgapi.controller;

import es.upm.tfgapi.model.User;
import es.upm.tfgapi.repository.UserRepository;
import es.upm.tfgapi.repository.TFGRepository;
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

    @CrossOrigin
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getDirectorInfo(@PathVariable String id) {
        try {
            Optional<User> userData = userRepository.findById(Long.parseLong(id));
            return userData
                    .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @GetMapping("/users")
    public ResponseEntity<List<User>> getDirectors(@RequestParam(required = false) String role) {
        try {
            List<User> directors = userRepository.findByRole(role);
            if (directors.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(directors, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}