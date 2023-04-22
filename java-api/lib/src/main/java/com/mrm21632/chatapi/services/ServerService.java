package com.mrm21632.chatapi.services;

import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mrm21632.chatapi.models.Server;
import com.mrm21632.chatapi.models.requests.ServerPostRequestBody;
import com.mrm21632.chatapi.utils.HibernateUtils;

public class ServerService {
    private static final Logger logger = LoggerFactory.getLogger(ServerService.class.getName());
    private static final Session session = HibernateUtils.getSessionFactory().openSession();

    /* === /servers === */
    public static List<Server> getAll() {
        String query = "SELECT s FROM Server s";
        return session.createQuery(query, Server.class)
            .getResultList();
    }

    public static Server addServer(ServerPostRequestBody body) {
        Server server = new Server()
            .setServerName(body.getServerName());

        Transaction tx = session.beginTransaction();
        try {
            session.persist(server);
            tx.commit();
            return server;
        } catch (Exception e) {
            logger.error("Exception occurred attempting to save new server to datastore", e);
            tx.rollback();
            return null;
        }
    }


    /* === /servers/:id === */
    public static Server getServer(String serverId) {
        String query = "SELECT s FROM Server s WHERE s.serverid = :id";
        return session.createQuery(query, Server.class)
            .setParameter("id", UUID.fromString(serverId))
            .uniqueResult();
    }

    public static void updateServer(Server server) {
        Transaction tx = session.beginTransaction();
        try {
            session.merge(server);
            tx.commit();
        } catch (Exception e) {
            logger.error("Exception occurred attempting to update server", e);
            tx.rollback();
        }
    }

    public static void deleteServer(String serverId) {
        Transaction tx = session.beginTransaction();
        try {
            Server server = session.find(Server.class, UUID.fromString(serverId));
            session.remove(server);
            tx.commit();
        } catch (Exception e) {
            logger.error("Exception occurred attempting to delete server", e);
            tx.rollback();
        }
    }
}
