#!/bin/bash
# Creates the two databases on first PostgreSQL startup
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
    CREATE DATABASE signature_db;
    CREATE DATABASE archive_db;
    GRANT ALL PRIVILEGES ON DATABASE signature_db TO $POSTGRES_USER;
    GRANT ALL PRIVILEGES ON DATABASE archive_db TO $POSTGRES_USER;
EOSQL
