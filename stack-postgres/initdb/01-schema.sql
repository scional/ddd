CREATE SCHEMA IF NOT EXISTS app AUTHORIZATION appuser;

ALTER DATABASE appdb SET search_path TO app, public;
ALTER ROLE appuser IN DATABASE appdb SET search_path TO app, public;

GRANT USAGE, CREATE ON SCHEMA app TO appuser;
GRANT ALL PRIVILEGES ON SCHEMA app TO appuser;