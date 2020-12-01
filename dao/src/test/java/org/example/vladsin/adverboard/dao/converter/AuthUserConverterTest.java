package org.example.vladsin.adverboard.dao.converter;

import org.example.vladsin.adverboard.dao.entity.AuthUserEntity;
import org.example.vladsin.adverboard.model.AuthUser;
import org.example.vladsin.adverboard.model.Role;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthUserConverterTest {

    @Test
    void fromEntity(){
        AuthUserEntity authUserEntity = new AuthUserEntity();
        authUserEntity.setId(null);
        authUserEntity.setLogin("login");
        authUserEntity.setPassword("pass");
        authUserEntity.setRole(Role.USER);
        authUserEntity.setUserId(1L);

        AuthUser authorizationUser = AuthUserConverter.fromEntity(authUserEntity);
        assertNotNull(authorizationUser);
        assertEquals(authorizationUser.getLogin(), authUserEntity.getLogin());
        assertEquals(authorizationUser.getPassword(), authUserEntity.getPassword());
        assertEquals(authorizationUser.getRole(), authUserEntity.getRole());
        assertEquals(authorizationUser.getUserId(), authUserEntity.getUserId());
    }

    @Test
    void toEntity(){
        AuthUser authUser = new AuthUser(null, "login", "pass", Role.USER, 1L);
        AuthUserEntity authUserEntity = AuthUserConverter.toEntity(authUser);
        assertNotNull(authUserEntity);
        assertEquals(authUser.getLogin(), authUserEntity.getLogin());
        assertEquals(authUser.getPassword(), authUserEntity.getPassword());
        assertEquals(authUser.getRole(), authUserEntity.getRole());
        assertEquals(authUser.getUserId(), authUserEntity.getUserId());
    }
}