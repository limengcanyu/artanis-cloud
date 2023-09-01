DELETE
FROM children;
DELETE
FROM user;

INSERT INTO children (id, tenant_id, name, user_id)
VALUES (1, 1, 'Jone', 1),
       (2, 1, 'Jack', 1),
       (3, 1, 'Jack2', 1),
       (4, 2, 'Jack', 15),
       (5, 3, 'Billie', 15);

INSERT INTO user (id, tenant_id, name, age, email)
VALUES (1, 1, 'Jone', 18, 'test1@baomidou.com'),
       (2, 1, 'Jack', 20, 'test2@baomidou.com'),
       (3, 1, 'Jack', 20, 'test2@baomidou.com'),
       (4, 1, 'Jack', 20, 'test2@baomidou.com'),
       (5, 1, 'Jack', 20, 'test2@baomidou.com'),
       (6, 1, 'Jack', 20, 'test2@baomidou.com'),
       (7, 1, 'Jack', 20, 'test2@baomidou.com'),
       (8, 1, 'Jack', 20, 'test2@baomidou.com'),
       (9, 2, 'Jack', 20, 'test2@baomidou.com'),
       (10, 2, 'Jack', 20, 'test2@baomidou.com'),
       (11, 2, 'Jack', 20, 'test2@baomidou.com'),
       (12, 2, 'Jack', 20, 'test2@baomidou.com'),
       (13, 2, 'Jack', 20, 'test2@baomidou.com'),
       (14, 2, 'Jack', 20, 'test2@baomidou.com'),
       (15, 3, 'Tom', 28, 'test3@baomidou.com'),
       (16, 3, 'Sandy', 21, 'test4@baomidou.com'),
       (17, 3, 'Billie', 24, 'test5@baomidou.com');

INSERT INTO user_addr (id, USER_ID, name)
VALUES (1, 1, 'addr1'),
       (2, 1, 'addr2');
