create table "user"
(
    id           uuid primary key,
    first_name       varchar   not null,
    last_name       varchar   not null,
    display_name varchar   not null,
    email        varchar unique,
    password     varchar   not null,
    is_verified  boolean default false,
    created      timestamp not null,
    updated      timestamp not null
);