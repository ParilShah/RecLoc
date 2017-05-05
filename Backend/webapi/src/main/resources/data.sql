-- ---------------------------------------------------Table Generation------------------------------------------------
-- DROP TABLE category IF EXISTS;
-- DROP TABLE location IF EXISTS;
-- DROP TABLE location_category IF EXISTS;
-- DROP TABLE location_user IF EXISTS;
-- DROP TABLE user IF EXISTS;
-- DROP TABLE user_category IF EXISTS;
-- DROP SEQUENCE IF EXISTS hibernate_sequence;
-- CREATE SEQUENCE hibernate_sequence START WITH 1 INCREMENT BY 1;
-- CREATE TABLE category (
--   category_id   BIGINT NOT NULL,
--   category_name VARCHAR(255),
--   PRIMARY KEY (category_id)
-- );
-- CREATE TABLE location (
--   location_id          INTEGER NOT NULL,
--   location_description VARCHAR(255),
--   location_name        VARCHAR(255),
--   PRIMARY KEY (location_id)
-- );
-- CREATE TABLE location_category (
--   location_id INTEGER NOT NULL,
--   category_id BIGINT  NOT NULL
-- );
-- CREATE TABLE location_user (
--   location_id INTEGER      NOT NULL,
--   device_id   VARCHAR(255) NOT NULL
-- );
-- CREATE TABLE user (
--   device_id VARCHAR(255) NOT NULL,
--   PRIMARY KEY (device_id)
-- );
-- CREATE TABLE user_category (
--   device_id   VARCHAR(255) NOT NULL,
--   category_id BIGINT       NOT NULL
-- );
-- ALTER TABLE location_category
--   ADD CONSTRAINT FK2mnvq127a66fnaq3w9mkgmst0 FOREIGN KEY (category_id) REFERENCES category;
-- ALTER TABLE location_category
--   ADD CONSTRAINT FK682ake3h6rwo9hw464at710ax FOREIGN KEY (location_id) REFERENCES location;
-- ALTER TABLE location_user
--   ADD CONSTRAINT FKovhalohcxc3fy7va7suj6bs0n FOREIGN KEY (device_id) REFERENCES user;
-- ALTER TABLE location_user
--   ADD CONSTRAINT FKl0ch5c4u1qyuvwysgsn5bfe7w FOREIGN KEY (location_id) REFERENCES location;
-- ALTER TABLE user_category
--   ADD CONSTRAINT FKjchjxphkf5owj1i5bp95g5mfs FOREIGN KEY (category_id) REFERENCES category;
-- ALTER TABLE user_category
--   ADD CONSTRAINT FK7nahxp79xlfhxamhx9mmihk7h FOREIGN KEY (device_id) REFERENCES user;

-- --------------------------------------------Table Generation end-------------------------------------------------

-- -----------------------Fake Data Insertion
INSERT INTO category (category_name, category_id) VALUES ('India', 5);
INSERT INTO category (category_name, category_id) VALUES ('Forest', 1);
-- 2017-05-02 21:37:15.552 TRACE 11056 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [VARCHAR] - [Forest]
-- 2017-05-02 21:37:15.552 TRACE 11056 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [2] as [BIGINT] - [1]
-- 2017-05-02 21:37:15.552 DEBUG 11056 --- [           main] org.hibernate.SQL                        : insert into category (category_name, category_id) values (?, ?)
INSERT INTO category (category_name, category_id) VALUES ('Beach', 2);
-- 2017-05-02 21:37:15.552 TRACE 11056 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [VARCHAR] - [Beach]
-- 2017-05-02 21:37:15.552 TRACE 11056 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [2] as [BIGINT] - [2]
-- 2017-05-02 21:37:15.552 DEBUG 11056 --- [           main] org.hibernate.SQL                        : insert into category (category_name, category_id) values (?, ?)
INSERT INTO category (category_name, category_id) VALUES ('Mountain', 3);
-- 2017-05-02 21:37:15.553 TRACE 11056 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [VARCHAR] - [Mountain]
-- 2017-05-02 21:37:15.553 TRACE 11056 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [2] as [BIGINT] - [3]
-- 2017-05-02 21:37:15.553 DEBUG 11056 --- [           main] org.hibernate.SQL                        : insert into category (category_name, category_id) values (?, ?)
INSERT INTO category (category_name, category_id) VALUES ('Disney', 4);
-- 2017-05-02 21:37:15.553 TRACE 11056 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [VARCHAR] - [Disney]
-- 2017-05-02 21:37:15.553 TRACE 11056 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [2] as [BIGINT] - [4]
-- 2017-05-02 21:37:15.563 DEBUG 11056 --- [           main] org.hibernate.SQL                        : select userdomain0_.device_id as device_i1_4_0_ from user userdomain0_ where userdomain0_.device_id=?
INSERT INTO user (device_id) VALUES (1234);
-- 2017-05-02 21:37:15.587 TRACE 11056 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [VARCHAR] - [1234]
-- 2017-05-02 21:37:15.589 DEBUG 11056 --- [           main] org.hibernate.SQL                        : insert into user_category (device_id, category_id) values (?, ?)
INSERT INTO user_category (device_id, category_id) VALUES (1234, 5);
-- 2017-05-02 21:37:15.589 TRACE 11056 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [VARCHAR] - [1234]
-- 2017-05-02 21:37:15.589 TRACE 11056 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [2] as [BIGINT] - [5]
-- 2017-05-02 21:37:15.590 DEBUG 11056 --- [           main] org.hibernate.SQL                        : insert into user_category (device_id, category_id) values (?, ?)
INSERT INTO user_category (device_id, category_id) VALUES (1234, 1);
-- 2017-05-02 21:37:15.590 TRACE 11056 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [VARCHAR] - [1234]
-- 2017-05-02 21:37:15.590 TRACE 11056 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [2] as [BIGINT] - [1]
-- 2017-05-02 21:37:15.590 DEBUG 11056 --- [           main] org.hibernate.SQL                        : insert into user_category (device_id, category_id) values (1234, 1)
INSERT INTO user_category (device_id, category_id) VALUES (1234, 2);
-- 2017-05-02 21:37:15.590 TRACE 11056 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [VARCHAR] - [1234]
-- 2017-05-02 21:37:15.590 TRACE 11056 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [2] as [BIGINT] - [2]
-- 2017-05-02 21:37:15.590 DEBUG 11056 --- [           main] org.hibernate.SQL                        : insert into user_category (device_id, category_id) values (1234, 1)
INSERT INTO user_category (device_id, category_id) VALUES (1234, 3);
-- 2017-05-02 21:37:15.590 TRACE 11056 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [VARCHAR] - [1234]
-- 2017-05-02 21:37:15.591 TRACE 11056 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [2] as [BIGINT] - [3]
-- 2017-05-02 21:37:15.591 DEBUG 11056 --- [           main] org.hibernate.SQL                        : insert into user_category (device_id, category_id) values (1234, 1)
INSERT INTO user_category (device_id, category_id) VALUES (1234, 4);
-- 2017-05-02 21:37:15.591 TRACE 11056 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [VARCHAR] - [1234]
-- 2017-05-02 21:37:15.591 TRACE 11056 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [2] as [BIGINT] - [4]
-- 2017-05-02 21:37:15.592 DEBUG 11056 --- [           main] org.hibernate.SQL                        : select userdomain0_.device_id as device_i1_4_0_ from user userdomain0_ where userdomain0_.device_id=?
SELECT userdomain0_.device_id AS device_i1_4_0_
FROM user userdomain0_
WHERE userdomain0_.device_id = 1235;
-- 2017-05-02 21:37:15.592 TRACE 11056 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [VARCHAR] - [1235]
-- 2017-05-02 21:37:15.593 DEBUG 11056 --- [           main] org.hibernate.SQL                        : select categorydo0_.category_id as category1_0_0_, categorydo0_.category_name as category2_0_0_ from category categorydo0_ where categorydo0_.category_id=?
SELECT
  categorydo0_.category_id   AS category1_0_0_,
  categorydo0_.category_name AS category2_0_0_
FROM category categorydo0_
WHERE categorydo0_.category_id = 5;
-- 2017-05-02 21:37:15.593 TRACE 11056 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [BIGINT] - [5]
-- 2017-05-02 21:37:15.593 TRACE 11056 --- [           main] o.h.type.descriptor.sql.BasicExtractor   : extracted value ([category2_0_0_] : [VARCHAR]) - [India]
-- 2017-05-02 21:37:15.593 TRACE 11056 --- [           main] org.hibernate.type.CollectionType        : Created collection wrapper: [csc258.domain.db.category.CategoryDomain.locationDomains#5]
-- 2017-05-02 21:37:15.593 TRACE 11056 --- [           main] org.hibernate.type.CollectionType        : Created collection wrapper: [csc258.domain.db.category.CategoryDomain.userDomains#5]
-- 2017-05-02 21:37:15.593 DEBUG 11056 --- [           main] org.hibernate.SQL                        : select categorydo0_.category_id as category1_0_0_, categorydo0_.category_name as category2_0_0_ from category categorydo0_ where categorydo0_.category_id=?
SELECT
  categorydo0_.category_id   AS category1_0_0_,
  categorydo0_.category_name AS category2_0_0_
FROM category categorydo0_
WHERE categorydo0_.category_id = 1;
-- 2017-05-02 21:37:15.593 TRACE 11056 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [BIGINT] - [1]
-- 2017-05-02 21:37:15.593 TRACE 11056 --- [           main] o.h.type.descriptor.sql.BasicExtractor   : extracted value ([category2_0_0_] : [VARCHAR]) - [Forest]
-- 2017-05-02 21:37:15.594 TRACE 11056 --- [           main] org.hibernate.type.CollectionType        : Created collection wrapper: [csc258.domain.db.category.CategoryDomain.locationDomains#1]
-- 2017-05-02 21:37:15.594 TRACE 11056 --- [           main] org.hibernate.type.CollectionType        : Created collection wrapper: [csc258.domain.db.category.CategoryDomain.userDomains#1]
-- 2017-05-02 21:37:15.594 DEBUG 11056 --- [           main] org.hibernate.SQL                        : insert into user (device_id) values (?)
INSERT INTO user (device_id) VALUES (1235);
-- 2017-05-02 21:37:15.594 TRACE 11056 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [VARCHAR] - [1235]
-- 2017-05-02 21:37:15.595 DEBUG 11056 --- [           main] org.hibernate.SQL                        : insert into user_category (device_id, category_id) values (?, ?)
INSERT INTO user_category (device_id, category_id) VALUES (1235, 5);
-- 2017-05-02 21:37:15.595 TRACE 11056 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [VARCHAR] - [1235]
-- 2017-05-02 21:37:15.595 TRACE 11056 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [2] as [BIGINT] - [5]
-- 2017-05-02 21:37:15.595 DEBUG 11056 --- [           main] org.hibernate.SQL                        : insert into user_category (device_id, category_id) values (?, ?)
INSERT INTO user_category (device_id, category_id) VALUES (1235, 1);
-- 2017-05-02 21:37:15.595 TRACE 11056 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [VARCHAR] - [1235]
-- 2017-05-02 21:37:15.595 TRACE 11056 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [2] as [BIGINT] - [1]
-- 2017-05-02 21:37:15.596 DEBUG 11056 --- [           main] org.hibernate.SQL                        : select userdomain0_.device_id as device_i1_4_0_ from user userdomain0_ where userdomain0_.device_id=?
INSERT INTO user (device_id) VALUES (1236);
-- 2017-05-02 21:37:15.598 TRACE 11056 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [VARCHAR] - [1236]
-- 2017-05-02 21:37:15.599 DEBUG 11056 --- [           main] org.hibernate.SQL                        : insert into user_category (device_id, category_id) values (?, ?)
INSERT INTO user_category (device_id, category_id) VALUES (1236, 5);
-- 2017-05-02 21:37:15.599 TRACE 11056 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [VARCHAR] - [1236]
-- 2017-05-02 21:37:15.599 TRACE 11056 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [2] as [BIGINT] - [5]
-- 2017-05-02 21:37:15.600 DEBUG 11056 --- [           main] org.hibernate.SQL                        : select userdomain0_.device_id as device_i1_4_0_ from user userdomain0_ where userdomain0_.device_id=?
INSERT INTO user (device_id) VALUES (1237);
-- 2017-05-02 21:37:15.603 TRACE 11056 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [VARCHAR] - [1237]
-- 2017-05-02 21:37:15.603 DEBUG 11056 --- [           main] org.hibernate.SQL                        : insert into user_category (device_id, category_id) values (?, ?)
INSERT INTO user_category (device_id, category_id) VALUES (1237, 5);
-- 2017-05-02 21:37:15.603 TRACE 11056 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [VARCHAR] - [1237]
-- 2017-05-02 21:37:15.603 TRACE 11056 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [2] as [BIGINT] - [5]
-- 2017-05-02 21:37:15.604 DEBUG 11056 --- [           main] org.hibernate.SQL                        : insert into user_category (device_id, category_id) values (1237, 5)
INSERT INTO user_category (device_id, category_id) VALUES (1237, 2);
-- 2017-05-02 21:37:15.604 TRACE 11056 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [VARCHAR] - [1237]
-- 2017-05-02 21:37:15.604 TRACE 11056 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [2] as [BIGINT] - [2]
-- 2017-05-02 21:37:15.604 DEBUG 11056 --- [           main] org.hibernate.SQL                        : insert into user_category (device_id, category_id) values (1237, 5)
INSERT INTO user_category (device_id, category_id) VALUES (1237, 3);
-- 2017-05-02 21:37:15.604 TRACE 11056 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [VARCHAR] - [1237]
-- 2017-05-02 21:37:15.604 TRACE 11056 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [2] as [BIGINT] - [3]