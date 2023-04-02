package com.mrm21632.chatapi.models;

import java.util.UUID;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Model class representing a chat server.
 */
@Data
@Accessors(chain = true)
public class Server {
    private UUID serverid;
    private String serverName;
}
