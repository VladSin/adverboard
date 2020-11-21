package org.example.vladsin.adverboard.dao.converter;

import org.example.vladsin.adverboard.dao.entity.AuthUserEntity;
import org.example.vladsin.adverboard.model.AuthUser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthUserConverterTest {

    @Test
    void fromEntity(){
        AuthUserEntity authUserEntity = new AuthUserEntity();
        authUserEntity.setId(null);
        authUserEntity.setLogin("login");
        authUserEntity.setPassword("pass");

        AuthUser authorizationUser = AuthUserConverter.fromEntity(authUserEntity);
        assertNotNull(authorizationUser);
        assertEquals(authorizationUser.getLogin(), authUserEntity.getLogin());
        assertEquals(authorizationUser.getPassword(), authUserEntity.getPassword());
    }

    @Test
    void toEntity(){
        AuthUser authUser = new AuthUser(null, "login", "pass");
        AuthUserEntity authUserEntity = AuthUserConverter.toEntity(authUser);
        assertNotNull(authUserEntity);
        assertEquals(authUser.getLogin(), authUserEntity.getLogin());
        assertEquals(authUser.getPassword(), authUserEntity.getPassword());
    }
}