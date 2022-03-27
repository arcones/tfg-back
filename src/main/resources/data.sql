INSERT INTO user (name, password, mail, role)
VALUES ('Pepa Perez', 'test', 'pepa@perez.com', 'Student'),
       ('Paca Lopez', 'test', 'paca@lopez.com', 'Student'),
       ('Lauria Rodriguez Lopez', 'test', 'lauria@rolo.com', 'Student'),
       ('Perry Mason', 'test', 'perry@mason.com', 'Student'),
       ('Jander Grijander', 'test', 'jander@grij.com', 'Director'),
       ('Macu Rolo', 'test', 'macu@rolo.com', 'Director');

INSERT INTO tfg (title, student_id, director_id, status)
VALUES ('El titulo de Pepa Perez', 1, 4, 'INIT_REQUESTED'),
       ('Otro titulo', 2, 5, 'INIT_APPROVED'),
       ('Otro titulo', 3, 4, 'INIT_REJECTED');
