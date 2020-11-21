package org.example.vladsin.adverboard.dao.converter;

import org.example.vladsin.adverboard.dao.entity.UserEntity;
import org.example.vladsin.adverboard.model.User;

public class UserConverter {

    public static User fromEntity(UserEntity userEntity){
        if(userEntity == null){
            return null;
        }
        return new User(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getEmail()
        );
    }

    public static UserEntity toEntity(User user){
        if (user == null){
            return null;
        }
        final UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId());
        userEntity.setName(user.getName());
        userEntity.setEmail(user.getEmail());
        return userEntity;
    }
}