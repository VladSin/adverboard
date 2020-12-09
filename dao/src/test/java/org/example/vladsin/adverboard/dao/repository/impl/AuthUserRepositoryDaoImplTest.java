package org.example.vladsin.adverboard.dao.repository.impl;

import org.example.vladsin.adverboard.dao.config.DaoConfig;
import org.example.vladsin.adverboard.dao.repository.AuthUserRepositoryDao;
import org.example.vladsin.adverboard.dao.repository.SecurityRepositoryDao;
import org.example.vladsin.adverboard.model.AuthUser;
import org.example.vladsin.adverboard.model.Role;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DaoConfig.class)
@Transactional
@Commit
class AuthUserRepositoryDaoImplTest {
    @Autowired
    private AuthUserRepositoryDao authUserRepositoryDao;

    @Autowired
    private SecurityRepositoryDao securityRepositoryDao;

    @Autowired
    SessionFactory sessionFactory;

    @Test
    void saveAuthUser() {
        final AuthUser authUserToSave = new AuthUser(null, "login", "password", Role.USER, 1L);
        final AuthUser savedAuthUser = authUserRepositoryDao.saveAuthUser(authUserToSave);
        assertEquals(authUserToSave.getLogin(), savedAuthUser.getLogin());
        assertEquals(authUserToSave.getPassword(), savedAuthUser.getPassword());
        assertEquals(authUserToSave.getRole(), savedAuthUser.getRole());
    }

    @Test
    void deleteAuthUser() {
        final AuthUser authUserToSave = new AuthUser(null, "login", "password", Role.USER, 1L);
        final AuthUser savedAuthUser = authUserRepositoryDao.saveAuthUser(authUserToSave);
        final Long id = savedAuthUser.getId();
        final AuthUser authorizationUser = authUserRepositoryDao.getAuthUser(id);
        assertNotNull(authorizationUser);
        sessionFactory.getCurrentSession().clear();

        final boolean deleted = authUserRepositoryDao.deleteAuthUser(id);
        assertTrue(deleted);

        final AuthUser afterDelete = authUserRepositoryDao.getAuthUser(id);
        assertNull(afterDelete);
    }

    @Test
    void updateAuthUser() {
        final AuthUser authUserToSave = new AuthUser(null, "login", "password", Role.USER, 1L);
        final AuthUser savedAuthUser = authUserRepositoryDao.saveAuthUser(authUserToSave);
        final Long id = savedAuthUser.getId();
        sessionFactory.getCurrentSession().clear();

        final AuthUser toUpdate = new AuthUser(id, "login2", "password2", Role.USER, 1L);
        final boolean updated = authUserRepositoryDao.updateAuthUser(toUpdate);
        assertTrue(updated);

        final AuthUser afterUpdate = authUserRepositoryDao.getAuthUser(id);
        assertEquals(toUpdate.getId(), afterUpdate.getId());
        assertEquals(toUpdate.getLogin(), afterUpdate.getLogin());
        assertEquals(toUpdate.getPassword(), afterUpdate.getPassword());
        assertEquals(toUpdate.getRole(), afterUpdate.getRole());
        assertEquals(toUpdate.getId(), afterUpdate.getId());
    }

    @Test
    void getById() {
        final AuthUser authUserToSave = new AuthUser(null, "login", "password", Role.USER, 1L);
        final AuthUser savedAuthUser = authUserRepositoryDao.saveAuthUser(authUserToSave);
        final Long id = savedAuthUser.getId();

        final AuthUser authorizationUser = authUserRepositoryDao.getAuthUser(id);
        assertNotNull(authorizationUser);

        assertEquals(authUserToSave.getLogin(), authorizationUser.getLogin());
        assertEquals(authUserToSave.getPassword(), authorizationUser.getPassword());
        assertEquals(authUserToSave.getRole(), authorizationUser.getRole());
        assertEquals(id, authorizationUser.getId());
    }

    @Test
    void getByUserId() {
        final AuthUser authUserToSave = new AuthUser(null, "login", "password", Role.USER, 9L);
        final AuthUser savedAuthUser = authUserRepositoryDao.saveAuthUser(authUserToSave);

        final AuthUser authorizationUser = securityRepositoryDao.getByUserId(savedAuthUser.getUserId());
        assertNotNull(authorizationUser);

        assertEquals(authUserToSave.getLogin(), authorizationUser.getLogin());
        assertEquals(authUserToSave.getPassword(), authorizationUser.getPassword());
        assertEquals(authUserToSave.getRole(), authorizationUser.getRole());
    }

    @Test
    void getByLogin() {
        final AuthUser authUserToSave = new AuthUser(null, "login9", "password9", Role.USER, 1L);
        final AuthUser savedAuthUser = authUserRepositoryDao.saveAuthUser(authUserToSave);

        final AuthUser authorizationUser = securityRepositoryDao.getByLogin(savedAuthUser.getLogin());
        assertNotNull(authorizationUser);

        assertEquals(authUserToSave.getLogin(), authorizationUser.getLogin());
        assertEquals(authUserToSave.getPassword(), authorizationUser.getPassword());
        assertEquals(authUserToSave.getRole(), authorizationUser.getRole());
    }
}