CREATE TABLE T_POST
(
    id         long primary key auto_increment,
    story      varchar(250),
    created_at timestamp not null,
    user_id    long      not null
);

CREATE TABLE T_COMMENT
(
    id         long primary key auto_increment,
    comment    varchar(250),
    created_at timestamp not null,
    user_id    long      not null,
    post_id    long,
    constraint fk_post FOREIGN KEY (id) REFERENCES t_post (id),
    constraint fk_user FOREIGN KEY (id) REFERENCES t_user (id)

);

CREATE TABLE T_LIKE
(
    id      long primary key auto_increment,
    user_id long not null,
    post_id long,
    constraint fk_post FOREIGN KEY (id) REFERENCES t_post (id),
    constraint fk_user FOREIGN KEY (id) REFERENCES t_user (id)
);

CREATE TABLE T_USER
(
    id         long primary key auto_increment,
    first_name varchar(250),
    last_name  varchar(250),
    email      varchar(250),
    password   varchar(250)
);
