create schema if not exists user_storage;

create table user_storage.user
(
    id uuid not null constraint user_id primary key,
    username varchar(200) not null
)