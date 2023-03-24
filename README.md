# A Basic Text-based Chat API, In Multiple Languages

While attempting to learn new languages, in particular Golang and Rust, I thought to myself: "What would be a really good way to both learn these new skills, and also refresh on some languages I've been using for a while?"

The first thing that came to mind was, "Make a generic API and implement it in all of the languages you're working with."

So that's what this repository is about. Each folder contains an implementation of the same API (detailed below) in a different backend language. The goal of this project is two-fold:

1. Gain an understanding of new languages, and refresh knowledge of other languages that I've used in the past.
2. Assess the benefits and drawbacks of using each language for API development.

Included in this repository is a simple database script which can be used for testing and verifying the different API implementations. The script was originally written with PostgreSQL in mind, though it can easily be translated to other database technologies (e.g., MySQL or T-SQL).

In the future, the plan is to also add a user interface to facilitate interacting with the APIs in a more user-friendly way.

## Setting up the Database

First, make sure PostgreSQL 13 _at a minimum_ is installed on your machine. On Windows, this can be accomplished either using the installer or an archive distribution for manual installation (which is what I use).

After this is done, there are only a few commands you need to run to get things set up. These steps assume you have a user named `postgres` set up, which is used throughout.

Initialization:

```shell
initdb -D /path/to/dbfolder -U postgres -W -E UTF8 -A scram-sha-256
```

Start the service:

```shell
pg_ctl -D /path/to/dbfolder -l /path/to/service/log start
```

Stop the service:

```shell
pg_ctl -D /path/to/dbfolder -m smart stop
```

Create the `chatdata` database:

```shell
createdb -U postgres chatdata
```

Run the included script to create the schema and tables:

```shell
psql -U postgres -d chatdata -a -f /path/to/chat-database.sql
```

Access the database (assumes you're using the default address of `localhost:5432`):

```shell
psql -h localhost -p 5432 -d chatdata -U postgres

# Alternatively
psql postgresql://postgres@localhost:5432/chatdata
```

## The API, In Detail

### Overview

So what exactly is this API? In short, it is a very simple, barebones, fully text-based chat API. This means no support for images (aside from providing hyperlinks), no avatars, and no detailed user profiles. Just servers with their own channels, the messages contained within, and user accounts. This project isn't really intended to construct a more complex system, so this will suffice.

There are a few more nuances than that, though. There are some special relationships, for instance, between these different entities. Servers have an associated list of both banned users (i.e., people who can't access the server or its channels) and moderators (i.e., people who are responsible for maintaining the server and its channels). Users can block and unblock other users, preventing them from seeing other users' messages across channels and servers alike. Moderators can ban users from servers.

The API can also be leveraged to query for messages in a given channel. This can be done by a given user ID, or by looking for certain character sequences in messages.
