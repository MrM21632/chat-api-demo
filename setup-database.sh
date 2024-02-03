#!/usr/bin/env bash

usage() {
    echo "$0 [-d <path>] [-l <path>] [-s <path>]" 1>&2
    echo "\td: Path to database server directory"
    echo "\tl: Path to database server logfile"
    echo "\ts: Path to database initialization script; defaults to current directory"
    echo "\th: Print this message and exit"
    exit 1
}

# Note: This script assumes a user named "postgres" already exists.
while getopts ":d:l:s:h:" option; do
    case "${option}" in
        d) # Location of base directory for database server
            d=${OPTARG}
            ;;
        l) # Location of logfile
            l=${OPTARG}
            ;;
        s) # Location of init script for database
            s=${OPTARG}
            ;;
        h) # Display help and exit
            usage
            ;;
       \?) # Invalid option
            echo "Error: invalid option provided."
            usage
            ;;
    esac
done
shift "$((OPTIND-1))"

if [ -z "${d}" ]; then
    d="$(pwd)/chatdata"
    mkdir -p $d
fi

if [ -z "${l}" ]; then
    l="$(pwd)/chatdata.log"
fi

if [ -z "${s}" ]; then
    s="$(pwd)/chat-database.sql"
fi

echo "Initializing database in directory ${d} with logfile ${l}"
initdb -D $d -U postgres -W -E UTF8 -A scram-sha-256
pg_ctl -D $d -l $l start
createdb -U postgres chatdata
psql -U postgres -d chatdata -a -f $s
echo "Database initialization complete."
echo "Start the database using the following command: pg_ctl start -l <logfile>"
echo "Access the database using the following command: psql -h localhost -p 5432 -d chatdata -U postgres"
