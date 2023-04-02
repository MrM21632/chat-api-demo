package com.mrm21632.chatapi.controllers;

import com.google.gson.Gson;
import com.mrm21632.chatapi.models.Server;
import com.mrm21632.chatapi.models.requests.ServerPostRequestBody;
import com.mrm21632.chatapi.services.ServerService;
import com.mrm21632.chatapi.utils.JsonTransformer;

import static spark.Spark.get;
import static spark.Spark.path;
import static spark.Spark.post;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerController {
    private static final Logger logger = LoggerFactory.getLogger(ServerController.class.getName());

    public static void run() {
        logger.info("Initializing Server controller.");
        path("/servers", () -> {
            get("", (req, res) -> {
                res.type("application/json");
                return ServerService.getAll(req, res);
            }, new JsonTransformer());
            post("", (req, res) -> {
                ServerPostRequestBody body = new Gson().fromJson(req.body(), ServerPostRequestBody.class);
                Server result = ServerService.add(body);
                res.type("application/json");
                res.status(201);
                return result;
            }, new JsonTransformer());
        });
    }
}
