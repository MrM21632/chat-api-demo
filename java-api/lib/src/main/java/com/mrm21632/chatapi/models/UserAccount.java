package com.mrm21632.chatapi.models;

import java.util.UUID;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Model class representing a user account.
 */
@Data
@Accessors(chain = true)
public class UserAccount {
    private UUID userid;
    private String username;
    private String email;
    private String password;
    private String passsalt;
}
