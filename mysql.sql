-- Chapter 32 (1)
CREATE TABLE users (
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    enabled  CHAR(1)  DEFAULT '1',
    PRIMARY KEY (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE authorities (
    username  VARCHAR(50) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    CONSTRAINT fk_authorities_users FOREIGN KEY (username) REFERENCES users(username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE UNIQUE INDEX ix_auth_username ON authorities (username, authority);

INSERT INTO users (username, password) VALUES ("user00", "pw00");
INSERT INTO users (username, password) VALUES ("member00", "pw00");
INSERT INTO users (username, password) VALUES ("admin00", "pw00");

INSERT INTO authorities (username, authority) VALUES ("user00", "ROLE_USER");
INSERT INTO authorities (username, authority) VALUES ("member00", "ROLE_MANAGER");
INSERT INTO authorities (username, authority) VALUES ("admin00", "ROLE_MANAGER");
INSERT INTO authorities (username, authority) VALUES ("admin00", "ROLE_ADMIN");

SELECT * FROM users;
SELECT * FROM authorities;

-- Chapter 32  (2)
CREATE TABLE tbl_member (
    userid     VARCHAR(50)  NOT NULL,
    userpw     VARCHAR(100) NOT NULL,
    username   VARCHAR(100) NOT NULL,
    regdate    TIMESTAMP    NOT NULL DEFAULT now(),
    updatedate TIMESTAMP    NOT NULL DEFAULT now(),
    enabled  CHAR(1)  DEFAULT '1',
    PRIMARY KEY (userid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE tbl_member_auth (
    userid VARCHAR(50) NOT NULL,
    auth VARCHAR(50) NOT NULL,
    CONSTRAINT fk_member_auth FOREIGN KEY (userid) REFERENCES tbl_member(userid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SELECT * FROM tbl_member;
SELECT * FROM tbl_member_auth;

--  Chapter 35
CREATE TABLE persistent_logins (
    username   VARCHAR(64) NOT NULL,
    series     VARCHAR(64) NOT NULL,
    token      VARCHAR(64) NOT NULL,
    last_used  TIMESTAMP   NOT NULL,
    PRIMARY KEY (series)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SELECT * FROM persistent_logins;