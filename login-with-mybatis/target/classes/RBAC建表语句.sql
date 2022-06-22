# role表建表语句
insert into role (id, name, name_zw) values (1, 'ROLE_admin', '管理员');
insert into role (id, name, name_zw) values (2, 'ROLE_user', '用户');

# user表建表语句
insert into user (id, username, password, account_non_expired, account_non_locked, credentials_non_expired, enabled) values (1, 'admin', '123456', 1, 1, 1, 1);
insert into user (id, username, password, account_non_expired, account_non_locked, credentials_non_expired, enabled) values (2, 'user', '123456', 1, 1, 1, 1);

# user_roles表建表语句
insert into user_roles (id, user_id, role_id) values (1, 1, 1);
insert into user_roles (id, user_id, role_id) values (2, 2, 2);

# resource表建表语句
insert into resource (id, pattern) values (1, '/admin/*');
insert into resource (id, pattern) values (2, '/user/*');

# resource_roles
insert into resource_roles (id, role_id, resource_id) values (1, 1, 1);
insert into resource_roles (id, role_id, resource_id) values (2, 2, 2);