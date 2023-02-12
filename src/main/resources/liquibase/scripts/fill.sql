-- liquibase formatted sql

--changeset dkirillov:1
CREATE TABLE users
(
    id SERIAL primary key,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    email VARCHAR(50),
    phone VARCHAR(50),
    reg_date VARCHAR(50),
    city VARCHAR (255),
    image VARCHAR(255),
    username VARCHAR(255),
    password VARCHAR(255),
    role INTEGER
)

--changeset dkirillov:2
CREATE TABLE ads
(
    pk  SERIAL primary key,
    image VARCHAR(255),
    price INTEGER,
    description VARCHAR(255),
    title VARCHAR(255)
)

--changeset dkirillov:3
CREATE TABLE ads_comments
(
    pk SERIAL primary key,
    created_at VARCHAR(255),
    text VARCHAR(255),
    author INTEGER
)

--changeset dkirillov:4
ALTER TABLE ads ADD COLUMN user_id SERIAL;
ALTER TABLE ads ADD FOREIGN KEY (user_id) REFERENCES users(id);

--changeset dkirillov:5
-- ALTER TABLE ads_comments ADD COLUMN user_id SERIAL;
ALTER TABLE ads_comments ADD COLUMN ads_id SERIAL;
-- ALTER TABLE ads_comments ADD FOREIGN KEY (user_id) REFERENCES users(id);
ALTER TABLE ads_comments ADD FOREIGN KEY (ads_id) REFERENCES ads(pk);

--changeset dkirillov:6
CREATE TABLE ads_images
(
    id VARCHAR(255) primary key,
    data oid,
    file_path VARCHAR(255),
    file_size BIGINT not null,
    media_type VARCHAR (255)
)

--changeset dkirillov:7
ALTER TABLE ads_images ADD COLUMN ads_pk SERIAL;
ALTER TABLE ads_images ADD FOREIGN KEY (ads_pk) REFERENCES ads(pk);

--changeset dkirillov:8
CREATE TABLE user_image
(
    id VARCHAR(255) primary key,
    file_size BIGINT,
    media_type VARCHAR(255),
    data oid
)

--changeset dkirillov:9
ALTER TABLE user_image ADD COLUMN user_id SERIAL;
ALTER TABLE user_image ADD FOREIGN KEY (user_id) REFERENCES users(id);
