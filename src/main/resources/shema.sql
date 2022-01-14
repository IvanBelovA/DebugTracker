DROP TABLE if EXISTS user CASCADE;
DROP TABLE if EXISTS project CASCADE;
DROP TABLE if EXISTS task CASCADE;
DROP TABLE if EXISTS priority CASCADE;
DROP TABLE if EXISTS type CASCADE;
DROP TABLE if EXISTS topic CASCADE;

CREATE TABLE user
(
    id SERIAL PRIMARY KEY,
    name varchar(250),
    last_name varchar(250),
    speciality varchar(250)
);

CREATE TABLE project
(
    id SERIAL PRIMARY KEY,
    name varchar(250)
);



CREATE TABLE topic
(
    id SERIAL PRIMARY KEY,
    title varchar(250),
    description varchar(250)
);

CREATE TABLE type
(
    id SERIAL PRIMARY KEY,
    title varchar(250)
);

CREATE TABLE priority
(
    id SERIAL PRIMARY KEY,
    name varchar(250)
);
CREATE TABLE task
(
    id SERIAL PRIMARY KEY,
    project_id integer,
    topic_id integer,
    type_id integer,
    priority_id integer,
    user_id integer,
    description varchar(250),
    FOREIGN KEY (project_id) REFERENCES project(id),
    FOREIGN KEY (topic_id) REFERENCES topic(id),
    FOREIGN KEY (type_id) REFERENCES type(id),
    FOREIGN KEY (priority_id) REFERENCES priority(id),
    FOREIGN KEY (user_id) REFERENCES user(id)
);