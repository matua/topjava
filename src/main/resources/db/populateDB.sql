DELETE
FROM user_roles;
DELETE
FROM users;
DELETE
FROM meals;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT INTO meals (date_time, description, calories, user_id)
VALUES ('2020-01-30 10:00:00', 'Завтрак', 500, 100000),
       ('2020-01-30 13:00:00', 'Обед', 1000, 100000),
       ('2020-01-30 20:00:00', 'Ужин', 500, 100000),
       ('2020-01-31 00:00:00', 'Еда на граничное значение', 100, 100000),
       ('2020-01-31 10:00:00', 'Завтрак', 1000, 100000),
       ('2020-01-31 13:00:00', 'Обед', 500, 100000),
       ('2020-01-31 20:00:00', 'Ужин', 410, 100000);

INSERT INTO meals (date_time, description, calories, user_id)
VALUES ('2020-01-12 09:00:00', 'Завтрак2', 50, 100001),
       ('2020-01-12 12:00:00', 'Обед2', 100, 100001),
       ('2020-01-12 11:00:00', 'Ужин2', 50, 100001),
       ('2020-01-13 00:00:00', 'Еда на граничное значение2', 10, 100001),
       ('2020-01-31 11:00:00', 'Завтрак2', 100, 100001),
       ('2020-01-31 15:00:00', 'Обед2', 50, 100001),
       ('2020-01-31 09:00:00', 'Ужин2', 41, 100001);