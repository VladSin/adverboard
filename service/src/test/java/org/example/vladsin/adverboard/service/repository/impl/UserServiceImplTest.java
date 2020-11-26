package org.example.vladsin.adverboard.service.repository.impl;

import org.example.vladsin.adverboard.dao.repository.UserDao;
import org.example.vladsin.adverboard.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {


    @Mock
    UserDao dao;

    @InjectMocks
    UserServiceImpl service;

    @Test
    void saveUser() {
        when(dao.getUser(1)).thenReturn(new User(1L, "User", "user@mail.ru"));
        final User userFromDb = service.getUser(1);
        assertNotNull(userFromDb);

        when(dao.saveUser(userFromDb)).thenReturn(userFromDb);
        final User user = service.saveUser(userFromDb);

        assertNotNull(user);
        assertEquals(userFromDb.getId(), user.getId());
        assertEquals(userFromDb.getName(), user.getName());
        assertEquals(userFromDb.getEmail(), user.getEmail());
    }

    @Test
    void deleteUser() {
        when(dao.getUser(1)).thenReturn(new User(1L, "User", "user@mail.ru"));
        final User userFromDb = service.getUser(1);
        assertNotNull(userFromDb);

        when(dao.deleteUser(1L)).thenReturn(true);
        final boolean delete = service.deleteUser(1L);
        assertTrue(delete);
    }

    @Test
    void updateUser() {
        when(dao.getUser(1)).thenReturn(new User(1L, "User", "user@mail.ru"));
        final User userFromDb = service.getUser(1);
        assertNotNull(userFromDb);

        when(dao.updateUser(userFromDb)).thenReturn(true);
        final boolean update = service.updateUser(userFromDb);
        assertTrue(update);
    }

    @Test
    void getUser() {
        when(dao.getUser(1)).thenReturn(new User(1L, "User",  "user@mail.ru"));
        final User userFromDb = service.getUser(1);
        assertNotNull(userFromDb);

        final User user = service.getUser(1);
        assertNotNull(user);
        assertEquals(userFromDb.getId(), user.getId());
        assertEquals(userFromDb.getName(), user.getName());
        assertEquals(userFromDb.getEmail(), user.getEmail());
    }

    @Test
    void getUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User(1L, "User1", "user1@mail.ru"));
        users.add(new User(2L, "User2", "user2@mail.ru"));
        when(dao.getUsers()).thenReturn(users);

        List<User> userDao = dao.getUsers();
        assertNotNull(userDao);
        for (int i = 0; i < userDao.size(); i++) {
            assertEquals(userDao.get(i).getId(), users.get(i).getId());
            assertEquals(userDao.get(i).getName(), users.get(i).getName());
            assertEquals(userDao.get(i).getEmail(), users.get(i).getEmail());
        }
    }
}