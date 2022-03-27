CREATE TABLE student
(
    id    INT AUTO_INCREMENT PRIMARY KEY,
    name  VARCHAR(250) NOT NULL,
    password VARCHAR(50) NOT NULL
);


CREATE TABLE tfg
(
    id    INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(250) NOT NULL,
    student_id INT,
    foreign key (student_id) references student(id)
);
