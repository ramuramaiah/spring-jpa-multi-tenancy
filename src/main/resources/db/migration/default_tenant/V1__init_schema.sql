CREATE TABLE user
(
    id                 int NOT NULL AUTO_INCREMENT,
    username           VARCHAR(255),
    password           VARCHAR(255),
    UNIQUE (username),
    PRIMARY KEY (id)
);
