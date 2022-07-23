CREATE extension IF NOT EXISTS pgcrypto;

UPDATE employee SET password = crypt(password, gen_salt('bf', 8))