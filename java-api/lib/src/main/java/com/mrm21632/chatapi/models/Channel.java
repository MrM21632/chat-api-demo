package com.mrm21632.chatapi.models;

import java.util.UUID;

import lombok.Data;

/**
 * Model class representing a server channel.
 */
@Data
public class Channel {
    private UUID channelid;
    private UUID serverid;
    private String channelName;
}
