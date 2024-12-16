CREATE TABLE products(
    id bigint PRIMARY KEY NOT NULL UNIQUE AUTO_INCREMENT,
    name VARCHAR(250) NOT NULL,
    description VARCHAR(250),
    price DOUBLE NOT NULL,
    in_stock tinyint NOT NULL
);