package com.everton.StarLog.controllers;

import com.everton.StarLog.entities.User;
import com.everton.StarLog.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody @Valid User user){
        return userService.createUser(user);
    }

    @GetMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<User> allUsers(){
        return userService.allUsers();
    }

    @ResponseBody
    @GetMapping("/{idClient}")
    @ResponseStatus(HttpStatus.OK)
    public User findUserById(@PathVariable Long idClient){
        return userService.findUserById(idClient);
    }

    @ResponseBody
    @PutMapping("/{idClient}")
    @ResponseStatus(HttpStatus.OK)
    public User updateUser(@PathVariable Long idClient,@RequestBody @Valid User user){
        return userService.updateClient(idClient, user);
    }

    @ResponseBody
    @DeleteMapping("/{idClient}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("idClient") Long idClient){
        userService.deleteUser(idClient);
    }


}
