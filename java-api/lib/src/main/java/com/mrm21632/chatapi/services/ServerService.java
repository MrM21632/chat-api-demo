package com.mrm21632.chatapi.services;

import java.util.List;
import java.util.UUID;

import org.hibernate.Session;

import com.mrm21632.chatapi.models.Server;
import com.mrm21632.chatapi.models.requests.ServerPostRequestBody;
import com.mrm21632.chatapi.utils.HibernateUtils;

import spark.Request;
import spark.Response;

public class ServerService {
    private static final Session session = HibernateUtils.getSessionFactory().openSession();

    public static List<Server> getAll(Request req, Response res) {
        String query = "SELECT s FROM Server s";
        return session.createQuery(query, Server.class)
            .getResultList();
    }

    public static Server getServer(Request req, Response res, String serverId) {
        String query = "SELECT s FROM Server s WHERE s.serverid = :id";
        return session.createQuery(query, Server.class)
            .setParameter("id", UUID.fromString(serverId))
            .uniqueResult();
    }

    public static Server add(ServerPostRequestBody body) {
        return null;
    }
}
