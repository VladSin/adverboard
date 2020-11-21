package org.example.vladsin.adverboard.dao.converter;

import org.example.vladsin.adverboard.dao.entity.UserEntity;
import org.example.vladsin.adverboard.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserConverterTest {

    @Test
    void fromEntity(){
        UserEntity userEntity = new UserEntity();
        userEntity.setId(null);
        userEntity.setName("name");
        userEntity.setEmail("email");

        User user = UserConverter.fromEntity(userEntity);
        assertNotNull(user);
        assertEquals(user.getName(), userEntity.getName());
        assertEquals(user.getEmail(), userEntity.getEmail());
    }

    @Test
    void toEntity(){
        User user = new User(null, "name", "email");
        UserEntity userEntity = UserConverter.toEntity(user);
        assertNotNull(userEntity);
        assertEquals(user.getName(), userEntity.getName());
        assertEquals(user.getEmail(), userEntity.getEmail());
    }
}