insert into category (name)
VALUES ('Gloves'),
       ('Wheelbarrow'),
       ('Lawn mower'),
       ('Shovel'),
       ('Watering can');

insert into user_account (email, name, password)
VALUES ('user@mail.com', 'user', '$2a$10$SKiSwQCQr0kli55Io2S0BORVfWj30YFt.hGMZZBiq.vmzoj8NB/BW');

insert into user_role (user_id, role_id)
VALUES (2, 2);