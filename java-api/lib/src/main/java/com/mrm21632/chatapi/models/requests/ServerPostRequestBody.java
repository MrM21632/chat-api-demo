package com.mrm21632.chatapi.models.requests;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ServerPostRequestBody {
    private String serverName;
}
