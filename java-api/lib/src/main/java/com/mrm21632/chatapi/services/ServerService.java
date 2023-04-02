package com.mrm21632.chatapi.services;

import java.util.List;
import java.util.UUID;

import com.mrm21632.chatapi.database.ChatApiDao;
import com.mrm21632.chatapi.models.Server;
import com.mrm21632.chatapi.models.requests.ServerPostRequestBody;

import spark.Request;
import spark.Response;

public class ServerService {
    private static ChatApiDao dao = new ChatApiDao();

    public static List<Server> getAll(Request req, Response res) {
        return dao.getAllServers();
    }

    public static Server add(ServerPostRequestBody body) {
        Server server = new Server()
            .setServerName(body.getServerName())
            .setServerid(UUID.randomUUID());
        dao.addServer(server);
        return server;
    }
}
