INSERT INTO role (name)
VALUES ('USER');

INSERT INTO employee (username, password)
VALUES ('IvanIvanov', '1234'), ('MakarAleksandrov', '4321');

INSERT INTO employee_role(employee_id, role_id)
VALUES (1, 1), (2, 1);

INSERT INTO meet_room(name)
VALUES ('Главная')