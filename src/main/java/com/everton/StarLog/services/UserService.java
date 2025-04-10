package com.everton.StarLog.services;

import ch.qos.logback.core.net.server.Client;
import com.everton.StarLog.entities.User;
import com.everton.StarLog.exceptions.StarLogException;
import com.everton.StarLog.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private UserRepository repository;

    public User createUser(User user){
        return repository.saveAndFlush(user);
    }

    public List<User> allUsers(){
        List<User> users = repository.findAll();

        if(users.isEmpty()){
            throw new StarLogException("No user found in the list.",404);
        }

        return  users;
    }

    public User findUserById(Long idUser){
        return repository.findById(idUser).orElseThrow(()->
                new StarLogException("Client not found",404));
    }

    public User updateClient(Long idUser, User userUpdate){
        User userExist = findUserById(idUser);

        userExist.setName(userUpdate.getName());
        userExist.setCpf(userUpdate.getCpf());
        userExist.setEmail(userUpdate.getEmail());

        return repository.saveAndFlush(userExist);


    }

    @Transactional
    public void deleteUser(Long idClient){
        User user = repository.findById(idClient).orElseThrow(()->
                new StarLogException("User not found",404));

        user.getFavoritesFilms().clear();
        repository.saveAndFlush(user);
        repository.delete(user);
    }
}
