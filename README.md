# tfg-api

## Requisitos
* Open JDK 11

## Funcionamiento
Levantar el servidor con el comando:

```bash
./mvnw clean install spring-boot:run -DskipTests=true
```

[Acceder a la BBDD](http://localhost:8080/h2-console) con la cadena de conexión y credenciales que podrá encontrar [aquí](./src/main/resources/application.properties) e insertar en la BBDD algunos datos de prueba:

```SQL
INSERT INTO user (name, password, mail, role)
VALUES ('Pepa Perez', 'test', 'pepa@perez.com', 'Student'),
       ('Macu Rolo', 'test', 'macu@rolo.com', 'Director');
```
