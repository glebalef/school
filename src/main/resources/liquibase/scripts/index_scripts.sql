-- liquibase formatted sql

- changeset gdvoynykh:1
    CREATE INDEX student_name_index ON student (name);

- changeset gdvoynykh:2
    CREATE INDEX faculty_name_color_index ON faculty (color, name);




