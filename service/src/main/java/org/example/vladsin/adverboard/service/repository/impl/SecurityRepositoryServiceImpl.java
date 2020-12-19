package org.example.vladsin.adverboard.service.repository.impl;


import org.example.vladsin.adverboard.dao.repository.AuthUserRepositoryDao;
import org.example.vladsin.adverboard.dao.repository.SecurityRepositoryDao;
import org.example.vladsin.adverboard.model.AuthUser;
import org.example.vladsin.adverboard.service.repository.SecurityRepositoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SecurityRepositoryServiceImpl implements SecurityRepositoryService {

    private final SecurityRepositoryDao securityRepositoryDao;
    private final AuthUserRepositoryDao authUserRepositoryDao;
    public SecurityRepositoryServiceImpl(SecurityRepositoryDao securityRepositoryDao, AuthUserRepositoryDao authUserRepositoryDao) {
        this.securityRepositoryDao = securityRepositoryDao;
        this.authUserRepositoryDao = authUserRepositoryDao;
    }

    @Override
    @Transactional
    public AuthUser login(String login, String password) {
        AuthUser user = securityRepositoryDao.getByLogin(login);
        if (user == null){
            return null;
        } else if (user.getPassword().equals(password)){
            return user;
        }
        return null;
    }

    @Override
    @Transactional
    public void updatePassword(Long userId, String newPassword) {
        AuthUser user = securityRepositoryDao.getByUserId(userId);
        user.setPassword(newPassword);
        authUserRepositoryDao.updateAuthUser(user);
    }

    @Override
    public boolean checkUniqLogin(String login) {
        AuthUser user = securityRepositoryDao.getByLogin(login);
        if (user == null){
            return true;
        }else {
            return false;
        }
    }
}
