package com.everton.StarLog.services;

import com.everton.StarLog.entities.User;
import com.everton.StarLog.exceptions.StarLogException;
import com.everton.StarLog.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository repository;

    @Test
    public void mustCreateUser() {
        when(repository.saveAndFlush(any(User.class))).thenReturn(new User());
        User user = userService.createUser(new User());
        assertNotNull(user);
    }

    @Test
    public void mustListUsers() {
        when(repository.findAll()).thenReturn(List.of(new User()));
        List<User> users = userService.allUsers();
        assertNotNull(users);
        assertFalse(users.isEmpty());
    }

    @Test
    public void doNotReturnUsersWhenListIsEmpty() {
        when(repository.findAll()).thenReturn(List.of());
        StarLogException exception = assertThrows(
                StarLogException.class, () -> userService.allUsers()
        );
        assertEquals("No user found in the list.", exception.getMessage());
        assertEquals(404, exception.getStatus());
    }

    @Test
    public void mustFindUserById() {
        User user = new User();
        user.setId(1L);
        when(repository.findById(anyLong())).thenReturn(Optional.of(user));

        User foundUser = userService.findUserById(1L);

        assertNotNull(foundUser);
        assertEquals(1L, foundUser.getId());
    }

    @Test
    public void doNotReturnUserWhenUserNotFound() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        StarLogException exception = assertThrows(StarLogException.class,
                ()->userService.findUserById(1L)
        );

        assertEquals("Client not found", exception.getMessage());
        assertEquals(404, exception.getStatus());
    }
}