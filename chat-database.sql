-- chat-database.sql
-- Baseline scripting for the chat API database. Requires PostgreSQL 13+

-- Cleanup before running the rest of the script
DROP SCHEMA IF EXISTS chat_data CASCADE;

-- Create the schema;
CREATE SCHEMA chat_data;

-- Setup for generating UUIDs as primary keys.
CREATE EXTENSION IF NOT EXISTS "pgcrypto" WITH SCHEMA chat_data;

-- User Accounts
CREATE TABLE chat_data.useracct (
    userid   uuid NOT NULL PRIMARY KEY DEFAULT gen_random_uuid(),
    username VARCHAR(32) NOT NULL UNIQUE,
    email    TEXT NOT NULL UNIQUE,

    -- Stored as a salted hashstring, along with its salt
    password TEXT NOT NULL,
	passsalt TEXT NOT NULL
);

-- Servers
CREATE TABLE chat_data.server (
    serverid    uuid NOT NULL PRIMARY KEY DEFAULT gen_random_uuid(),
    server_name TEXT NOT NULL UNIQUE
);

-- Channels
CREATE TABLE chat_data.channel (
    channelid    uuid NOT NULL PRIMARY KEY DEFAULT gen_random_uuid(),
    serverid     uuid NOT NULL,
    channel_name TEXT NOT NULL UNIQUE
);

-- User Messages within a given Channel
CREATE TABLE chat_data.message (
    messageid          uuid NOT NULL PRIMARY KEY DEFAULT gen_random_uuid(),
    channelid          uuid NOT NULL,
    userid             uuid NOT NULL,
    contents           TEXT NOT NULL,
    created_time       TIMESTAMP WITH TIME ZONE NOT NULL,
    last_modified_time TIMESTAMP WITH TIME ZONE NOT NULL
);

-- Managing blocklists for users
CREATE TABLE chat_data.user_blocklist (
    userid1      uuid NOT NULL,  -- Blocker ID
    userid2      uuid NOT NULL,  -- Blocked ID
    created_time TIMESTAMP WITH TIME ZONE NOT NULL,
    PRIMARY KEY(userid1, userid2)
);

-- Managing banned users for servers
CREATE TABLE chat_data.server_banlist (
    serverid     uuid NOT NULL,
    userid       uuid NOT NULL,
    created_time TIMESTAMP WITH TIME ZONE NOT NULL,
    PRIMARY KEY(serverid, userid)
);

-- Managing moderators for servers
CREATE TABLE chat_data.server_moderator (
    serverid     uuid NOT NULL,
    userid       uuid NOT NULL,
    created_time TIMESTAMP WITH TIME ZONE NOT NULL,
    PRIMARY KEY(serverid, userid)
);
