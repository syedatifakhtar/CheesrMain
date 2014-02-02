CREATE TABLE IF NOT EXISTS atif.cheese
(ID BIGINT not null auto_increment
,NAME VARCHAR(255)
,DESCRIPTION VARCHAR(255)
,MODIFIED_DATE DATETIME
,INSERT_DATE DATETIME
,PRIMARY KEY(ID));

CREATE TABLE IF NOT EXISTS atif.user
(ID BIGINT not null auto_increment
,USERNAME VARCHAR(40) NOT NULL
,EMAIL VARCHAR(100) UNIQUE NOT NULL
,PASSWORD VARCHAR(20) NOT NULL
,ADDRESS VARCHAR(255)
,ACTIVE CHAR(1)
,MODIFIED_DATE DATETIME
,INSERT_DATE DATETIME
,PRIMARY KEY(ID));

DROP TRIGGER IF EXISTS atif.CHEESE_ONUPDATE_TRIGGER;
DROP TRIGGER IF EXISTS atif.CHEESE_ONINSERT_TRIGGER;
CREATE 
	TRIGGER atif.CHEESE_ONUPDATE_TRIGGER 
	 BEFORE UPDATE
	ON atif.cheese FOR EACH row
	SET new.MODIFIED_DATE=NOW();

CREATE 
	TRIGGER atif.CHEESE_ONINSERT_TRIGGER 
	 BEFORE INSERT
	ON atif.cheese FOR EACH row
	SET new.INSERT_DATE=NOW(), new.MODIFIED_DATE=NOW();