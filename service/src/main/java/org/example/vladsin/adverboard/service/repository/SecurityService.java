package org.example.vladsin.adverboard.service.repository;

import org.example.vladsin.adverboard.model.AuthUser;

public interface SecurityService {

    AuthUser login(String login, String password);

    void updatePassword(Long userId, String newPassword);

    boolean checkUniqLogin(String login);
}
