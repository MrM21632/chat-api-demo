package com.mrm21632.chatapi.services;

import java.util.List;

import com.mrm21632.chatapi.database.ChatApiDao;
import com.mrm21632.chatapi.models.Server;

import spark.Request;
import spark.Response;

public class ServerService {
    private static ChatApiDao dao = new ChatApiDao();

    public static List<Server> getAll(Request req, Response res) {
        return dao.getAllServers();
    }
}
