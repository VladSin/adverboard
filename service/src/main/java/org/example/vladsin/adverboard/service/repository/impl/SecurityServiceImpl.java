package org.example.vladsin.adverboard.service.repository.impl;


import org.example.vladsin.adverboard.dao.repository.AuthUserDao;
import org.example.vladsin.adverboard.dao.repository.SecurityDao;
import org.example.vladsin.adverboard.model.AuthUser;
import org.example.vladsin.adverboard.service.repository.SecurityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SecurityServiceImpl implements SecurityService {

    private final SecurityDao securityDao;
    private final AuthUserDao authUserDao;
    public SecurityServiceImpl(SecurityDao securityDao, AuthUserDao authUserDao) {
        this.securityDao = securityDao;
        this.authUserDao = authUserDao;
    }

    @Override
    @Transactional
    public AuthUser login(String login, String password) {
        AuthUser user = securityDao.getByLogin(login);
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
        AuthUser user = securityDao.getByUserId(userId);
        user.setPassword(newPassword);
        authUserDao.updateAuthUser(user);
    }
}
