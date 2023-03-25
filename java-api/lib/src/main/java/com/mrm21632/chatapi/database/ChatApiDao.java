package com.mrm21632.chatapi.database;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sql2o.Sql2o;

/**
 * Provides a DAO for accessing information from the chat database.
 */
public class ChatApiDao {
    private static final Logger logger = LoggerFactory.getLogger(ChatApiDao.class.getName());
    private static Sql2o sql2o;

    static {
        try {
            Properties properties = new Properties();
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            InputStream stream = loader.getResourceAsStream("/database.properties");
            properties.load(stream);

            int port = Integer.parseInt(properties.getProperty("database.port", "5432"));
            String host = properties.getProperty("database.host", "localhost");
            String path = properties.getProperty("database.path", "chatdata");
            String username = properties.getProperty("database.username", "postgres");
            String password = properties.getProperty("database.password", "password");

            sql2o = new Sql2o(
                "jdbc:postgresql://" + host + ":" + port + "/" + path,
                username,
                password
            );
        } catch (IOException ioe) {
            logger.error("Failed to load properties from file database.properties", ioe);
        } catch (Exception e) {
            logger.error("Unexpected error occurred", e);
        }
    }
}
