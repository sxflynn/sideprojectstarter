BEGIN TRANSACTION;

-- IF YOU ADD MORE TABLES IN YOUR CUSTOM.SQL, ADD THEM HERE ⬇️
DROP TABLE IF EXISTS greetings, users;
DROP SEQUENCE IF EXISTS seq_user_id;
-- IF YOU ADD MORE TABLES IN YOUR CUSTOM.SQL, ADD THEM HERE ⬆️


CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  START WITH 1001
  NO MAXVALUE;

CREATE TABLE users (
	user_id int NOT NULL DEFAULT nextval('seq_user_id'),
	username varchar(50) UNIQUE NOT NULL,
	password_hash varchar(200) NOT NULL,
	role varchar(20),
	CONSTRAINT PK_user PRIMARY KEY (user_id),
	CONSTRAINT UQ_username UNIQUE (username)
);


-- DELETE IF YOU WOULD LIKE ⬇️
CREATE TABLE greetings (
    message text
);

INSERT INTO greetings (message) VALUES ('Hello Postgres!');
-- DELETE IF YOU WOULD LIKE ⬆️



COMMIT;



