package com.mrm21632.chatapi.services;

import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mrm21632.chatapi.models.UserAccount;
import com.mrm21632.chatapi.models.requests.UserAccountPostRequestBody;
import com.mrm21632.chatapi.utils.HibernateUtils;

public class UserAccountService {
    private static final Logger logger = LoggerFactory.getLogger(UserAccountService.class.getName());
    private static final Session session = HibernateUtils.getSessionFactory().openSession();

    /* === /users === */
    public static List<UserAccount> getAll() {
        String query = "SELECT u FROM UserAccount u";
        return session.createQuery(query, UserAccount.class)
            .getResultList();
    }

    public static UserAccount addUser(UserAccountPostRequestBody body) {
        UserAccount account = new UserAccount()
            .setUsername(body.getUsername())
            .setEmail(body.getEmail())
            .setPassword(body.getPassword())
            .setPasssalt(body.getPasssalt());

        Transaction tx = session.beginTransaction();
        try {
            session.persist(account);
            tx.commit();
            return account;
        } catch (Exception e) {
            logger.error("Exception occurred attempting to save new user account to datastore", e);
            tx.rollback();
            return null;
        }
    }


    /* === /users/:id === */
    public static UserAccount getUser(String userId) {
        String query = "SELECT u FROM UserAccount u WHERE u.userid = :id";
        return session.createQuery(query, UserAccount.class)
            .setParameter("id", UUID.fromString(userId))
            .uniqueResult();
    }

    public static void updateUser(UserAccount user) {
        Transaction tx = session.beginTransaction();
        try {
            session.merge(user);
            tx.commit();
        } catch (Exception e) {
            logger.error("Exception occurred attempting to update user account", e);
            tx.rollback();
        }
    }

    public static void deleteUser(String userId) {
        Transaction tx = session.beginTransaction();
        try {
            UserAccount user = session.find(UserAccount.class, UUID.fromString(userId));
            session.remove(user);
            tx.commit();
        } catch (Exception e) {
            logger.error("Exception occurred attempting to delete user account", e);
            tx.rollback();
        }
    }
}
