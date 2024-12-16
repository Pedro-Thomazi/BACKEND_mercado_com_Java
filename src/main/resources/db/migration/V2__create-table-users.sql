CREATE TABLE users(
    id bigint PRIMARY KEY NOT NULL UNIQUE auto_increment,
    name VARCHAR(250) NOT NULL,
    email VARCHAR(250) NOT NULL UNIQUE,
    password VARCHAR(250) NOT NULL,
    active tinyint NOT NULL,
    status VARCHAR(250) NOT NULL
);