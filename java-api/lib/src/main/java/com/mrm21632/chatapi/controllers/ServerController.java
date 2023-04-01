package com.mrm21632.chatapi.controllers;

import com.mrm21632.chatapi.services.ServerService;

import static spark.Spark.get;

public class ServerController {
    public static void run() {
        get("/servers", (req, res) -> ServerService.getAll(req, res));
    }
}
