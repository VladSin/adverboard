package org.example.vladsin.adverboard.service.repository.impl;

import org.example.vladsin.adverboard.dao.repository.AuthUserRepositoryDao;
import org.example.vladsin.adverboard.model.AuthUser;
import org.example.vladsin.adverboard.model.Role;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthUserRepositoryServiceImplTest {

    @Mock
    AuthUserRepositoryDao dao;

    @InjectMocks
    AuthUserRepositoryServiceImpl service;

    @Test
    void saveAuthUser() {
        when(dao.getAuthUser(1)).thenReturn(new AuthUser(1L,"login", "password", Role.USER, 1L));
        final AuthUser userFromDb = service.getAuthUser(1);
        assertNotNull(userFromDb);

        when(dao.saveAuthUser(userFromDb)).thenReturn(userFromDb);
        final AuthUser user = service.saveAuthUser(userFromDb);

        assertNotNull(user);
        assertEquals(userFromDb.getId(), user.getId());
        assertEquals(userFromDb.getLogin(), user.getLogin());
        assertEquals(userFromDb.getPassword(), user.getPassword());
        assertEquals(userFromDb.getRole(), user.getRole());
        assertEquals(userFromDb.getUserId(), user.getUserId());
    }

    @Test
    void getAuthUser() {
        when(dao.getAuthUser(1)).thenReturn(new AuthUser(1L,"login", "password", Role.USER, 1L));
        final AuthUser userFromDb = service.getAuthUser(1);
        assertNotNull(userFromDb);

        final AuthUser user = service.getAuthUser(1);
        assertNotNull(user);
        assertEquals(userFromDb.getId(), user.getId());
        assertEquals(userFromDb.getLogin(), user.getLogin());
        assertEquals(userFromDb.getPassword(), user.getPassword());
        assertEquals(userFromDb.getRole(), user.getRole());
        assertEquals(userFromDb.getUserId(), user.getUserId());
    }
    @Test
    void updateAuthUser() {
        when(dao.getAuthUser(1)).thenReturn(new AuthUser(1L,"login", "password", Role.USER, 1L));
        final AuthUser userFromDb = service.getAuthUser(1);
        assertNotNull(userFromDb);

        when(dao.updateAuthUser(userFromDb)).thenReturn(true);
        final boolean update = service.updateAuthUser(userFromDb);
        assertTrue(update);
    }

    @Test
    void deleteAuthUser() {
        when(dao.getAuthUser(1)).thenReturn(new AuthUser(1L,"login", "password", Role.USER, 1L));
        final AuthUser userFromDb = service.getAuthUser(1);
        assertNotNull(userFromDb);

        when(dao.deleteAuthUser(1L)).thenReturn(true);
        final boolean delete = service.deleteAuthUser(1L);
        assertTrue(delete);
    }
}