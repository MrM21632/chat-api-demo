package com.mrm21632.chatapi.models;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Model class representing a chat server.
 */
@Entity
@Table(name = "server", schema = "chat_data", catalog = "chatdata")
@Data
@Accessors(chain = true)
public class Server {
    @Id
    @GeneratedValue
    private UUID serverid;

    @Column(nullable = false, unique = true, name = "server_name")
    private String serverName;
}
