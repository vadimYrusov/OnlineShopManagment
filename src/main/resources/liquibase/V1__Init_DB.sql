drop table if exists role cascade;
drop table if exists shop_item cascade;
drop table if exists user_account cascade;
drop table if exists user_role cascade;

create table category
(
    id   bigserial    not null,
    name varchar(255) not null,
    primary key (id)
);
create table role
(
    id   bigserial    not null,
    name varchar(255) not null,
    primary key (id)
);
create table shop_item
(
    id          bigserial not null,
    category    varchar(255),
    description varchar(255),
    image_name  varchar(255),
    price       int8,
    title       varchar(255),
    primary key (id)
);
create table user_account
(
    id       bigserial    not null,
    email    varchar(255) not null,
    name     varchar(255) not null,
    password varchar(255),
    primary key (id)
);
create table user_role
(
    user_id int8 not null,
    role_id int8 not null
);

alter table category
    add constraint UK_46ccwnsi9409t36lurvtyljak unique (name);
alter table role
    add constraint UK_8sewwnpamngi6b1dwaa88askk unique (name);
alter table user_account
    add constraint UK_hl02wv5hym99ys465woijmfib unique (email);
alter table user_role
    add constraint FKa68196081fvovjhkek5m97n3y foreign key (role_id) references role;
alter table user_role
    add constraint FK7ojmv1m1vrxfl3kvt5bi5ur73 foreign key (user_id) references user_account;