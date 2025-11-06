CREATE TABLE tb_user
(
    id       TEXT PRIMARY KEY UNIQUE NOT NULL,
    active   BOOLEAN                 NOT NULL,
    name     TEXT                    NOT NULL,
    email    TEXT                    NOT NULL UNIQUE,
    password TEXT                    NOT NULL,
    role     TEXT                    NOT NULL
);
