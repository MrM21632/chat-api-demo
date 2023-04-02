package com.mrm21632.chatapi.models;

import java.util.Date;
import java.util.UUID;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Model class representing a chat message.
 */
@Data
@Accessors(chain = true)
public class Message {
    private UUID messageid;
    private UUID channelid;
    private UUID userid;
    private String contents;
    private Date createdTime;
    private Date lastModifiedTime;
}
