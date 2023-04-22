package com.mrm21632.chatapi.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.mrm21632.chatapi.models.UserAccount;
import com.mrm21632.chatapi.models.requests.UserAccountPostRequestBody;
import com.mrm21632.chatapi.services.UserAccountService;
import com.mrm21632.chatapi.utils.JsonTransformer;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.path;
import static spark.Spark.post;
import static spark.Spark.put;

public class UserAccountController {
    private static final Logger logger = LoggerFactory.getLogger(UserAccountController.class.getName());

    public static void run() {
        logger.info("Initializing UserAccount controller");
        path("/users", () -> {
            get("", (req, res) -> {
                res.type("application/json");
                return UserAccountService.getAll();
            }, new JsonTransformer());

            post("", (req, res) -> {
                UserAccountPostRequestBody body = new Gson().fromJson(req.body(), UserAccountPostRequestBody.class);
                UserAccount result = UserAccountService.addUser(body);
                res.type("application/json");
                res.status(201);
                return result;
            }, new JsonTransformer());


            get("/:id", (req, res) -> {
                res.type("application/json");
                return UserAccountService.getUser(req.params(":id"));
            }, new JsonTransformer());

            put("/:id", (req, res) -> {
                res.type("application/json");
                res.status(204);

                UserAccount user = new Gson().fromJson(req.body(), UserAccount.class);
                UserAccountService.updateUser(user);
                return null;
            }, new JsonTransformer());

            delete("/:id", (req, res) -> {
                res.type("application/json");
                res.status(204);

                UserAccountService.deleteUser(req.params(":id"));
                return null;
            }, new JsonTransformer());
        });
    }
}
