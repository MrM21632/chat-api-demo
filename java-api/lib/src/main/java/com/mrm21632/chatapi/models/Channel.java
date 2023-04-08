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
 * Model class representing a server channel.
 */
@Entity
@Table(name = "channel", schema = "chat_data")
@Data
@Accessors(chain = true)
public class Channel {
    @Id
    @GeneratedValue
    private UUID channelid;

    @Column(nullable = false)
    private UUID serverid;

    @Column(nullable = false, unique = true, name = "channel_name")
    private String channelName;
}
