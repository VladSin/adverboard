package org.example.vladsin.adverboard.dao.repository;

import org.example.vladsin.adverboard.dao.config.DaoConfig;
import org.example.vladsin.adverboard.model.User;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DaoConfig.class)
@Transactional
@Commit
class UserDaoImplTest {

    @Autowired
    private UserDao userDao;

    @Autowired
    SessionFactory sessionFactory;

    @Test
    void saveUser() {
        final User userToSave = new User(null, "name", "email");
        final User savedUser = userDao.saveUser(userToSave);

        assertEquals(userToSave.getName(), savedUser.getName());
        assertEquals(userToSave.getEmail(), savedUser.getEmail());
    }

    @Test
    void deleteUser() {
        final User userToSave = new User(null, "name", "email");
        final User savedUser = userDao.saveUser(userToSave);
        final Long id = savedUser.getId();
        sessionFactory.getCurrentSession().clear();

        final boolean deleted = userDao.deleteUser(id);
        assertTrue(deleted);

        final User afterDeleted = userDao.getUser(id);
        assertNull(afterDeleted);
    }

    @Test
    void updateUser() {
        final User userToSave = new User(null, "name", "email");
        final User savedUser = userDao.saveUser(userToSave);
        final Long id = savedUser.getId();
        sessionFactory.getCurrentSession().clear();

        final User toUpdate = new User(id, "name", "email");
        final boolean update = userDao.updateUser(toUpdate);
        assertTrue(update);

        final User afterUpdate = userDao.getUser(id);

        assertEquals(toUpdate.getName(), afterUpdate.getName());
        assertEquals(toUpdate.getEmail(), afterUpdate.getEmail());
    }

    @Test
    void getUser() {
        final User userToSave = new User(null, "name",  "email");
        final User savedUser = userDao.saveUser(userToSave);
        final Long id = savedUser.getId();

        final User user = userDao.getUser(id);
        assertNotNull(user);
        assertEquals(userToSave.getName(), user.getName());
        assertEquals(userToSave.getEmail(), user.getEmail());
    }

    @Test
    void getList(){
        List<User> users = new ArrayList<>();
        users.add(new User(null, "User1", "user1@mail.ru"));
        users.add(new User(null, "User2", "user2@mail.ru"));

        List<User> userList = new ArrayList<>();
        for (User u: users) {
            userList.add(userDao.saveUser(u));
        }

        assertNotNull(userList);
        for (int i = 0; i < userList.size(); i++) {
            assertEquals(userList.get(i).getName(), users.get(i).getName());
            assertEquals(userList.get(i).getEmail(), users.get(i).getEmail());
        }

        users = userDao.getUsers();
        assertNotNull(users);
    }
}