insert into permission(name)
values ('role:create'),
       ('role:read'),
       ('role:update'),
       ('role:delete'),
       ('permission:create'),
       ('permission:read'),
       ('permission:update'),
       ('permission:delete');

insert into role(name)
values ('ADMIN');
insert into role(name)
values ('USER');

insert into role_permission(role_id, permission_id)
values ((select name from role where name = 'ADMIN'), (select name from permission where name = 'role:create')),
       ((select name from role where name = 'ADMIN'), (select name from permission where name = 'role:read')),
       ((select name from role where name = 'ADMIN'), (select name from permission where name = 'role:update')),
       ((select name from role where name = 'ADMIN'), (select name from permission where name = 'role:delete')),
       ((select name from role where name = 'ADMIN'), (select name from permission where name = 'permission:create')),
       ((select name from role where name = 'ADMIN'), (select name from permission where name = 'permission:read')),
       ((select name from role where name = 'ADMIN'), (select name from permission where name = 'permission:update')),
       ((select name from role where name = 'ADMIN'), (select name from permission where name = 'permission:delete'));