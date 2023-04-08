package com.mrm21632.chatapi.models;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Model class representing a chat message.
 */
@Entity
@Table(name = "message", schema = "chat_data")
@Data
@Accessors(chain = true)
public class Message {
    @Id
    @GeneratedValue
    private UUID messageid;

    @Column(nullable = false)
    private UUID channelid;

    @Column(nullable = false)
    private UUID userid;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String contents;

    @Column(nullable = false, name = "created_time")
    private Date createdTime;

    @Column(nullable = false, name = "last_modified_time")
    private Date lastModifiedTime;
}
