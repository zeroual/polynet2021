CREATE TABLE t_post
(
    id         long primary key auto_increment,
    story      varchar(250),
    created_at timestamp not null,
    user_id    long      not null
);

CREATE TABLE t_comment
(
    id         long primary key auto_increment,
    comment    varchar(250),
    created_at timestamp not null,
    user_id    long      not null,
    post_id    long

);

CREATE TABLE t_user
(
    id         long primary key auto_increment,
    first_name varchar(250),
    last_name  varchar(250),
    email      varchar(250),
    password   varchar(250)
);
