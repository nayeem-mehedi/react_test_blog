DROP TABLE IF EXISTS USERS;
CREATE TABLE USERS
(
  `USER_ID`  INT AUTO_INCREMENT PRIMARY KEY,
  `USERNAME` VARCHAR(250) NOT NULL UNIQUE,
  `PASSWORD` VARCHAR(250) NOT NULL,
  `NAME`     VARCHAR(250) NOT NULL,
  `STATUS`   INT          NOT NULL, --[1] PENDING [2] ACTIVE [3] DELETED
  `ROLE`     VARCHAR(250) NOT NULL,
  `CREATED`  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `UPDATED`  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
);

-- DROP TABLE IF EXISTS roles;
-- CREATE TABLE roles (
--   `ROLE_ID` INT AUTO_INCREMENT PRIMARY KEY,
--   `NAME` VARCHAR(250) NOT NULL
-- );

-- DROP TABLE IF EXISTS users_roles;
-- CREATE TABLE users_roles (
--    `ID` INT AUTO_INCREMENT PRIMARY KEY,
--    `USER_ID` INT NOT NULL,
--    `ROLE_ID` INT NOT NULL
-- );

DROP TABLE IF EXISTS ARTICLES;
CREATE TABLE ARTICLES
(
  `ID`             INT AUTO_INCREMENT PRIMARY KEY,
  `TITLE`          VARCHAR(400) NOT NULL,
  `SUMMARY`        VARCHAR(700) NOT NULL,
  `COVERIMAGE`     VARCHAR(1000) NOT NULL,
  `AUTHOR`         INT          NOT NULL,
  `BODY`           VARCHAR,
  `APPROVAL_STATE` INT          NOT NULL, --[0] PENDING [1] ACTIVE
  `DELETE_FLAG`    INT          NOT NULL, --[0] NOT DELETED [1] DELETED
  `CREATED`        TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `UPDATED`        TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
);

DROP TABLE IF EXISTS USER_ARTICLES;
CREATE TABLE USER_ARTICLES
(
  `ID`         INT AUTO_INCREMENT PRIMARY KEY,
  `USER_ID`    INT NOT NULL,
  `ARTICLE_ID` INT NOT NULL
);

DROP TABLE IF EXISTS COMMENTS;
CREATE TABLE COMMENTS
(
  `ID`         INT AUTO_INCREMENT PRIMARY KEY,
  `ARTICLE_ID` INT          NOT NULL,
  `USER_ID`    INT          NOT NULL,
  `BODY`       VARCHAR(400) NOT NULL,
  `CREATED`    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `UPDATED`    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
);

-- DROP TABLE IF EXISTS ARTICLE_COMMENTS;
-- CREATE TABLE ARTICLE_COMMENTS (
--      `ID` INT AUTO_INCREMENT PRIMARY  KEY,
--      `ARTICLE_ID` INT NOT NULL,
--      `COMMENTS_ID` INT NOT NULL
-- );

--OPTIONAL
DROP TABLE IF EXISTS REACTIONS;
CREATE TABLE REACTIONS
(
  `ID`         INT AUTO_INCREMENT PRIMARY KEY,
  `USER_ID`    INT NOT NULL,
  `ARTICLE_ID` INT NOT NULL,
  `TYPE`       INT NOT NULL ---[1] LIKE [2] DisLike
);
--
-- DROP TABLE IF EXISTS ARTICLE_LIKES;
-- CREATE TABLE ARTICLE_LIKES
-- (
--   `ID`         INT AUTO_INCREMENT PRIMARY KEY,
--   `ARTICLE_ID` INT NOT NULL,
--   `LIKES_ID`   INT NOT NULL
-- );

--- username:pass -> dummy:dummy
INSERT INTO USERS(USERNAME, PASSWORD, NAME, STATUS, ROLE)
VALUES ('dummy', '$2a$10$3zHzb.Npv1hfZbLEU5qsdOju/tk2je6W6PnNnY.c1ujWPcZh4PL6e', 'dummy_account', 2, 'ADMIN');