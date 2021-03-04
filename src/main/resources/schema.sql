DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS task;
DROP TABLE IF EXISTS task_list;
DROP TABLE IF EXISTS user_task_list;
DROP TABLE IF EXISTS token;
CREATE TABLE user
(
    id        INT IDENTITY(1,1) PRIMARY KEY,
    email     VARCHAR(255) NOT NULL UNIQUE,
    password  VARCHAR(255),
    name      VARCHAR(255),
    user_role VARCHAR(255),
    is_active BIT
);
CREATE TABLE task
(
    id                INT IDENTITY(1,1) PRIMARY KEY,
    name              VARCHAR(255),
    description       VARCHAR(255),
    term              date,
    data_od_execution date,
    is_done           BIT,
    is_important      BIT,
    is_urgent         BIT,
    task_list_id      INT
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
    user_id      INT,
    task_list_id INT
);
CREATE TABLE token
(
    id      INT IDENTITY(1,1) PRIMARY KEY,
    token   VARCHAR(255),
    user_id INT
)

