create schema if not exists exposition_storage;

create table exposition_storage.user
(
    id uuid not null constraint user_id primary key,
    username varchar(200) not null
)