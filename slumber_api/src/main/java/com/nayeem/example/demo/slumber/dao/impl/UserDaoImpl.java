package com.nayeem.example.demo.slumber.dao.impl;

import com.nayeem.example.demo.slumber.constant.UserStatus;
import com.nayeem.example.demo.slumber.dao.UserDao;
import com.nayeem.example.demo.slumber.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User getActiveBloggerById(Long id) {
        String hql = "FROM User U WHERE U.id = :id AND U.status = :status AND U.role = :role";
        return entityManager
                .createQuery(hql, User.class)
                .setParameter("id", id)
                .setParameter("status", UserStatus.ACTIVE.getValue())
                .setParameter("role", "BLOGGER")
                .getSingleResult();
    }

    @Override
    public User getUserByUsername(String username) {
        String hql = "FROM User U WHERE U.username = :username";
        return entityManager.createQuery(hql, User.class).setParameter("username", username).getSingleResult();
    }

    @Override
    public User getActiveUserByUsername(String username) {
        String hql = "FROM User U WHERE U.username = :username AND U.status = :status";
        return entityManager.createQuery(hql, User.class)
                .setParameter("username", username)
                .setParameter("status", UserStatus.ACTIVE.getValue())
                .getSingleResult();
    }

    @Override
    public User saveUser(User user) {
        if (user.getId() == null) {
            entityManager.persist(user);
        } else {
            user = entityManager.merge(user);
        }
        return user;
    }

    @Override
    public List<User> getAllBloggers() {
        String hql = "FROM User U WHERE U.role = :role";
        return entityManager.createQuery(hql, User.class).setParameter("role", "BLOGGER").getResultList();
    }

    @Override
    public List<User> getAllActiveBloggers() {
        String hql = "FROM User U WHERE U.role = :role AND U.status = :status";
        return entityManager
                .createQuery(hql, User.class)
                .setParameter("role", "BLOGGER")
                .setParameter("status", UserStatus.ACTIVE.getValue())
                .getResultList();
    }

    @Override
    public List<User> getAllAdmins() {
        String hql = "FROM User U WHERE U.role = :role";
        return entityManager.createQuery(hql, User.class).setParameter("role", "ADMIN").getResultList();
    }
}
