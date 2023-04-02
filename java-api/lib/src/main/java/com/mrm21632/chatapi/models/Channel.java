package com.mrm21632.chatapi.models;

import java.util.UUID;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Model class representing a server channel.
 */
@Data
@Accessors(chain = true)
public class Channel {
    private UUID channelid;
    private UUID serverid;
    private String channelName;
}
