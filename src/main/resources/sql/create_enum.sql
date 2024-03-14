DO $$
BEGIN

    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'access_enum') THEN
        CREATE TYPE access_enum AS ENUM ('Manager', 'Worker');
    END IF;

    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'status_enum') THEN
        CREATE TYPE status_enum AS ENUM ('Accept', 'Prepare', 'Ready');
    END IF;

END $$;

