package com.mrm21632.chatapi.controllers;

import com.google.gson.Gson;
import com.mrm21632.chatapi.models.Server;
import com.mrm21632.chatapi.models.requests.ServerPostRequestBody;
import com.mrm21632.chatapi.services.ServerService;
import com.mrm21632.chatapi.utils.JsonTransformer;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.path;
import static spark.Spark.post;
import static spark.Spark.put;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerController {
    private static final Logger logger = LoggerFactory.getLogger(ServerController.class.getName());

    public static void run() {
        logger.info("Initializing Server controller.");
        path("/servers", () -> {
            get("", (req, res) -> {
                res.type("application/json");
                return ServerService.getAll();
            }, new JsonTransformer());

            post("", (req, res) -> {
                ServerPostRequestBody body = new Gson().fromJson(req.body(), ServerPostRequestBody.class);
                Server result = ServerService.addServer(body);
                res.type("application/json");
                res.status(201);
                return result;
            }, new JsonTransformer());


            get("/:id", (req, res) -> {
                res.type("application/json");
                return ServerService.getServer(req.params(":id"));
            }, new JsonTransformer());

            put("/:id", (req, res) -> {
                res.type("application/json");
                res.status(204);

                Server server = new Gson().fromJson(req.body(), Server.class);
                ServerService.updateServer(server);
                return null;
            }, new JsonTransformer());

            delete("/:id", (req, res) -> {
                res.type("application/json");
                res.status(204);

                ServerService.deleteServer(req.params(":id"));
                return null;
            }, new JsonTransformer());
        });
    }
}
