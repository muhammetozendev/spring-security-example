create table if not exists users
(
    id       int auto_increment,
    email    text,
    password text,
    primary key (id)
);

create table if not exists roles
(
    id   int auto_increment,
    name text,
    primary key (id)
);

create table if not exists  user_roles
(
    user_id int,
    role_id int,
    foreign key (user_id) references users (id),
    foreign key (role_id) references roles (id),
    primary key (user_id, role_id)
);