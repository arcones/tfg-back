INSERT INTO user (name, password, mail, role)
VALUES ('Pepa Perez', 'test', 'pepa@perez.com', 'Student'),
       ('Paca Lopez', 'test', 'paca@lopez.com', 'Student'),
       ('Lauria Rodriguez Lopez', 'test', 'lauria@rolo.com', 'Student'),
       ('Perry Mason', 'test', 'perry@mason.com', 'Director'),
       ('Jander Grijander', 'test', 'jander@grij.com', 'Director'),
       ('Macu Rolo', 'test', 'macu@rolo.com', 'Director'),
       ('Poor Lady', 'test', 'poor@lady.com', 'Student'),
       ('Artie Nomerechaces', 'test', 'artie@loser.com', 'Student');

INSERT INTO user (name, password, mail, role)
VALUES ('Akinator', 'test', 'aki@nat.or', 'Student');

UPDATE TFG SET status='INIT_REJECTED' where student_id=8

SELECT * FROM TFG WHERE STATUS='INIT_REQUESTED'

INSERT INTO tfg (title, student_id, director_id, status)
VALUES ('El titulo de Pepa Perez', 1, 6, 'INIT_REQUESTED'),
       ('Otro titulo', 2, 5, 'INIT_APPROVED'),
       ('El título q me rechazaron', 3, 4, 'INIT_REJECTED'),
       ('El título que me han aprobado', 3, 4, 'INIT_APPROVED'),
       ('Rechazo 1', 8, 5, 'INIT_REJECTED'),
       ('Rechazo 2', 8, 5, 'INIT_REJECTED')
       ;

