package es.upm.tfgback.controller;

import es.upm.tfgback.model.User;
import es.upm.tfgback.model.TFG;
import es.upm.tfgback.repository.UserRepository;
import es.upm.tfgback.repository.TFGRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@Tag(name = "User", description = "The user controller")
@RequestMapping("/api")
public class UserController {

    final UserRepository userRepository;
    final TFGRepository tfgRepository;

    public UserController(UserRepository userRepository, TFGRepository tfgRepository) {
        this.userRepository = userRepository;
        this.tfgRepository = tfgRepository;
    }

    @CrossOrigin
    @Operation(summary = "Login for users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Credentials are correct"),
            @ApiResponse(responseCode = "404", description = "Credentials doesn't match with any user in the database")
    })
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
    @Operation(summary = "Get user's information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User information provided"),
            @ApiResponse(responseCode = "404", description = "There is no user with that ID")
    })
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getDirectorInfo(@PathVariable long id) {
        try {
            Optional<User> userData = userRepository.findById(id);
            return userData
                    .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}