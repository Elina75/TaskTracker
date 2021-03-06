CREATE TABLE project
(
    id         BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name       VARCHAR(255),
    start_date date,
    end_date   date,
    status     VARCHAR(255),
    priority   INTEGER,
    CONSTRAINT pk_project PRIMARY KEY (id)
);

CREATE TABLE task
(
    id          BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name        VARCHAR(255),
    description TEXT,
    status      VARCHAR(255),
    priority    INTEGER,
    project_id  BIGINT NOT NULL,
    CONSTRAINT pk_task PRIMARY KEY (id),
    CONSTRAINT fk_project FOREIGN KEY(project_id) REFERENCES project(id)
);

