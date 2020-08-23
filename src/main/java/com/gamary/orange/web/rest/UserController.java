package com.gamary.orange.web.rest;

import com.gamary.orange.domain.User;
import com.gamary.orange.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Slf4j
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/users")
    public ResponseEntity<User> createOrUpdateUser(@Valid @RequestBody User user){
        log.info("Rest Call: Create or update user with {}",user);
        try{
            return ResponseEntity.ok(userService.createOrUpdateUser(user));
        }catch (RuntimeException ex){
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping("/users")
    public ResponseEntity<Page<User>> getUsers(Pageable pageable){
        log.info("Rest Call: Get users with {}",pageable);
         return ResponseEntity.ok(userService.getUsers(pageable));
    }
}
