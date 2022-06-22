create table if not exists role (
	id bigint primary key auto_increment,
    name varchar(45) not null unique,
    name_zw varchar(45) not null
);

create table if not exists user (
	id bigint primary key auto_increment,
    username varchar(45) not null unique,
    password varchar(45) not null,
    account_non_expired tinyint not null,
    account_non_locked tinyint not null,
    credentials_non_expired tinyint not null,
    enabled tinyint not null
);

create table if not exists user_roles (
    id bigint primary key auto_increment,
    user_id bigint not null,
    role_id bigint not null
);

create table if not exists resource (
    id bigint primary key auto_increment,
    pattern varchar(255)
);

create table if not exists resource_roles (
    id bigint primary key auto_increment,
    role_id bigint not null,
    resource_id bigint not null,
    index role_id (role_id),
    index resource_id (resource_id)
);