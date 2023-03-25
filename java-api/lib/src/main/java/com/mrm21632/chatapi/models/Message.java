package com.mrm21632.chatapi.models;

import java.util.Date;
import java.util.UUID;

import lombok.Data;

/**
 * Model class representing a chat message.
 */
@Data
public class Message {
    private UUID messageid;
    private UUID channelid;
    private UUID userid;
    private String contents;
    private Date createdTime;
    private Date lastModifiedTime;
}
