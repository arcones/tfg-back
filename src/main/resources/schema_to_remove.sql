CREATE TABLE user
(
    id    INT AUTO_INCREMENT PRIMARY KEY,
    name  VARCHAR(250) NOT NULL,
    password VARCHAR(50) NOT NULL,
    mail VARCHAR(250) NOT NULL,
    role VARCHAR(50) NOT NULL -- One of STUDENT, DIRECTOR, COMMITTEE, SECRETARY
);

CREATE TABLE tfg
(
    id    INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(250) NOT NULL,
    student_id INT,
    director_id INT,
    status VARCHAR(50), -- One of INIT_REQUESTED, INIT_REJECTED, INIT_APPROVED, READ_REQUESTED, READ_APPROVED, READ_REJECTED
    foreign key (student_id) references user(id),
    foreign key (director_id) references user(id)
);
