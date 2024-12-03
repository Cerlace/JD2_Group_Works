CREATE SCHEMA IF NOT EXISTS test;
SET SCHEMA test;
CREATE TABLE address
(
    id     INT AUTO_INCREMENT
        PRIMARY KEY,
    street VARCHAR(255) NULL,
    house  INT          NULL
);
INSERT INTO address (id, street, house)
VALUES (101, 'Test 1 Street', 10),
       (102, 'Test 2 Street', 20),
       (103, 'Test 3 Street', 30);

CREATE TABLE people
(
    id      INT AUTO_INCREMENT
        PRIMARY KEY,
    name    VARCHAR(255) NULL,
    surname VARCHAR(255) NULL,
    age     INT          NULL
);
INSERT INTO people (id, name, surname, age)
VALUES (101, 'Test 1 Name', 'Test 1 Surname', 10),
       (102, 'Test 2 Name', 'Test 2 Surname', 20),
       (103, 'Test 3 Name', 'Test 3 Surname', 30);