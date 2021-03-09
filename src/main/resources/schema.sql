DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS task;
DROP TABLE IF EXISTS task_list;
DROP TABLE IF EXISTS user_task_list;
DROP TABLE IF EXISTS token;
CREATE TABLE user
(
    id        INT IDENTITY(1,1) PRIMARY KEY,
    email     VARCHAR(255) NOT NULL UNIQUE,
    password  VARCHAR(255) NOT NULL,
    name      VARCHAR(255),
    user_role VARCHAR(255),
    is_active BIT
);
CREATE TABLE task
(
    id                INT IDENTITY(1,1) PRIMARY KEY,
    name              VARCHAR(255) NOT NULL ,
    description       VARCHAR(255),
    term              timestamp NOT NULL,
    data_od_execution timestamp,
    is_done           BIT       NOT NULL,
    is_important      BIT       NOT NULL,
    is_urgent         BIT       NOT NULL,
    task_list_id      INT       NOT NULL
);
CREATE TABLE task_list
(
    id          INT IDENTITY(1,1) PRIMARY KEY,
    name        VARCHAR(255),
    description VARCHAR(255)
);
CREATE TABLE user_task_list
(
    id           INT IDENTITY(1,1) PRIMARY KEY,
    user_id      INT NOT NULL,
    task_list_id INT NOT NULL
);
CREATE TABLE token
(
    id      INT IDENTITY(1,1) PRIMARY KEY,
    value   VARCHAR(255) NOT NULL,
    user_id INT          NOT NULL
)

