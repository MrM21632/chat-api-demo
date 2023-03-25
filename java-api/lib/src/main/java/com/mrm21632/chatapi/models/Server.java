package com.mrm21632.chatapi.models;

import java.util.UUID;

import lombok.Data;

/**
 * Model class representing a chat server.
 */
@Data
public class Server {
    private UUID serverid;
    private String serverName;
}
