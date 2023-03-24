# Endpoints

This document provides an overview of all endpoints and HTTP methods supported by the API. Note that, as this project progresses and is refined, more endpoints may be added in the future as needed to better flesh out functionality.

## Servers

### /servers

* `GET`: Returns all servers the API recognizes. Response payload will be of the following structure:

```json
[
    {
        "server_name": "example-server",
        "serverid": "11111111-2222-3333-4444-555566667777"
    }
]
```

* `POST`: Creates a new server. Request payload should be of the following structure:

```json
{
    "server_name": "example-server"
}
```

### /servers/\<id\>

* `GET`: Returns the server with given ID. Response payload will be of the following structure:

```json
{
    "server_name": "example-server",
    "serverid": "11111111-2222-3333-4444-555566667777"
}
```

* `PUT` or `PATCH`: Updates the server (i.e., just its name) with given ID. Request payload will be of the following structure:

```json
// PATCH (example)
{
    "server_name": "example-server"
}
```

* `DELETE`: Deletes the server.

### /servers/\<id\>/channels

* `GET`: Returns the channels associated with the given server. Response payload will be of the following structure:

```json
[
    {
        "channel_name": "example-channel",
        "channelid": "11111111-2222-3333-4444-555566667777",
        "serverid": "11111111-2222-3333-4444-555566667777"
    }
]
```

### /servers/\<id\>/moderators

* `GET`: Returns the moderators of this server. Response payload will be of the following structure:

```json
[
    {
        "userid": "11111111-2222-3333-4444-555566667777",
        "username": "example-user",
        "email": "user@email.com",
        "password": "6b3a55e0261b0304143f805a24924d0c1c44524821305f31d9277843b8a10f4e",
        "passsalt": "testsalt"
    }
]
```

* `POST`: Designates the a user as a moderator of the server. Request payload will be of the following structure:

```json
{
    "userid": "11111111-2222-3333-4444-555566667777"
}
```

### /servers/\<serverid\>/moderators/\<userid\>

* `GET`: Returns the moderator with given ID for this server. Response payload will be of the following structure:

```json
{
    "userid": "11111111-2222-3333-4444-555566667777",
    "username": "example-user",
    "email": "user@email.com",
    "password": "6b3a55e0261b0304143f805a24924d0c1c44524821305f31d9277843b8a10f4e",
    "passsalt": "testsalt"
}
```

* `DELETE`: Removes the given moderator from this server's moderator list.

### /servers/\<id\>/banned_users

* `GET`: Returns the users banned from this server. Response payload will be of the following structure:

```json
[
    {
        "userid": "11111111-2222-3333-4444-555566667777",
        "username": "example-user",
        "email": "user@email.com",
        "password": "6b3a55e0261b0304143f805a24924d0c1c44524821305f31d9277843b8a10f4e",
        "passsalt": "testsalt"
    }
]
```

* `POST`: Ban a user from this server. Request payload will be of the following structure:

```json
{
    "userid": "11111111-2222-3333-4444-555566667777"
}
```

### /servers/\<serverid\>/banned_users/\<userid\>

* `GET`: Returns the banned user with given ID for this server. Response payload will be of the following structure:

```json
{
    "userid": "11111111-2222-3333-4444-555566667777",
    "username": "example-user",
    "email": "user@email.com",
    "password": "6b3a55e0261b0304143f805a24924d0c1c44524821305f31d9277843b8a10f4e",
    "passsalt": "testsalt"
}
```

* `DELETE`: Unbans the user from this server.

## Channels

### /channels

* `GET`: Returns all channels the API recognizes. Response payload will be of the following structure:

```json
[
    {
        "channel_name": "example-channel",
        "channelid": "11111111-2222-3333-4444-555566667777",
        "serverid": "11111111-2222-3333-4444-555566667777"
    }
]
```

* `POST`: Creates a new channel. Request payload should be of the following structure:

```json
{
    "channel_name": "example-channel",
    "serverid": "11111111-2222-3333-4444-555566667777"
}
```

### /channels/\<id\>

* `GET`: Returns the channel with given ID. Response payload will be of the following structure:

```json
{
    "channel_name": "example-channel",
    "channelid": "11111111-2222-3333-4444-555566667777",
    "serverid": "11111111-2222-3333-4444-555566667777"
}
```

* `PUT` or `PATCH`: Updates the channel (i.e., just its name) with given ID. Request payload will be of the following structure:

```json
// PATCH (example)
{
    "channel_name": "example-channel"
}
```

* `DELETE`: Deletes the channel.

### /channels/\<id\>/messages

* `GET`: Returns the messages posted to this channel. Response payload will be of the following structure:

```json
[
    {
        "contents": "This is a message.",
        "created_time": "2023-03-24T14:56:34Z",
        "last_modified_time": "2023-03-24T14:56:34Z",
        "messageid": "11111111-2222-3333-4444-555566667777",
        "channelid": "11111111-2222-3333-4444-555566667777",
        "userid": "11111111-2222-3333-4444-555566667777"
    }
]
```

* `POST`: Post a new message to this channel. Request payload will be of the following structure:

```json
{
    "contents": "This is a message.",
    "created_time": "2023-03-24T14:56:34Z",
    "last_modified_time": "2023-03-24T14:56:34Z",
    "channelid": "11111111-2222-3333-4444-555566667777",
    "userid": "11111111-2222-3333-4444-555566667777"
}
```

### /channels/\<channelid\>/messages/\<messageid\>

* `GET`: Returns the message with given ID posted to this channel. Response payload will be of the following structure:

```json
{
    "contents": "This is a message.",
    "created_time": "2023-03-24T14:56:34Z",
    "last_modified_time": "2023-03-24T14:56:34Z",
    "messageid": "11111111-2222-3333-4444-555566667777",
    "channelid": "11111111-2222-3333-4444-555566667777",
    "userid": "11111111-2222-3333-4444-555566667777"
}
```

* `PUT` or `PATCH`: Updates the message (i.e., its contents) with given ID posted to this channel. Request payload will be of the following structure:

```json
// PUT
{
    "contents": "This is a message.",
    "created_time": "2023-03-24T14:56:34Z",
    "last_modified_time": "2023-03-24T14:56:34Z",
    "messageid": "11111111-2222-3333-4444-555566667777",
    "channelid": "11111111-2222-3333-4444-555566667777",
    "userid": "11111111-2222-3333-4444-555566667777"
}

// PATCH (example)
{
    "contents": "This is a message.",
}
```

* `DELETE`: Deletes the message.

### /channels/\<channelid\>/user_messages/\<userid\>

* `GET`: Returns the messages posted to this channel by the given user. Response payload will be of the following structure:

```json
[
    {
        "contents": "This is a message.",
        "created_time": "2023-03-24T14:56:34Z",
        "last_modified_time": "2023-03-24T14:56:34Z",
        "messageid": "11111111-2222-3333-4444-555566667777",
        "channelid": "11111111-2222-3333-4444-555566667777",
        "userid": "11111111-2222-3333-4444-555566667777"
    }
]
```

## Users

### /users

* `GET`: Returns all user accounts the API recognizes. Response payload will be of the following structure:

```json
[
    {
        "userid": "11111111-2222-3333-4444-555566667777",
        "username": "example-user",
        "email": "user@email.com",
        "password": "6b3a55e0261b0304143f805a24924d0c1c44524821305f31d9277843b8a10f4e",
        "passsalt": "testsalt"
    }
]
```

* `POST`: Create a new user account. Request payload will be of the following structure:

```json
{
    "username": "example-user",
    "email": "user@email.com",
    "password": "6b3a55e0261b0304143f805a24924d0c1c44524821305f31d9277843b8a10f4e",
    "passsalt": "testsalt"
}
```

### /users/\<id\>

* `GET`: Returns the user with given ID. Response payload will be of the following structure:

```json
{
    "userid": "11111111-2222-3333-4444-555566667777",
    "username": "example-user",
    "email": "user@email.com",
    "password": "6b3a55e0261b0304143f805a24924d0c1c44524821305f31d9277843b8a10f4e",
    "passsalt": "testsalt"
}
```

* `PUT` or `PATCH`: Updates the user with given ID. Request payload will be of the following structure:

```json
// PATCH (example)
{
    "username": "example-user"
}
```

* `DELETE`: Deletes the user account. This will propagate as needed through the rest of the API.
