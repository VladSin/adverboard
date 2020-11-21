package org.example.vladsin.adverboard.dao.converter;

import org.example.vladsin.adverboard.dao.entity.AuthUserEntity;
import org.example.vladsin.adverboard.model.AuthUser;

public class AuthUserConverter {

    public static AuthUser fromEntity(AuthUserEntity authUserEntity){
        if(authUserEntity == null){
            return null;
        }
        return new AuthUser(
                authUserEntity.getId(),
                authUserEntity.getLogin(),
                authUserEntity.getPassword()
        );
    }

    public static AuthUserEntity toEntity(AuthUser authUser){
        if (authUser == null){
            return null;
        }
        final AuthUserEntity authorizationUserEntity = new AuthUserEntity();
        authorizationUserEntity.setId(authUser.getId());
        authorizationUserEntity.setLogin(authUser.getLogin());
        authorizationUserEntity.setPassword(authUser.getPassword());
        return authorizationUserEntity;
    }
}
