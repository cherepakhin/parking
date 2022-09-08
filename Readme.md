## Простой проект "Машины на парковке"

Несколько парковок. Машины занимают места, выезжают. Кол-во мест на парковке ограничено.

## Примечания

Инициализация БД:
````shell
create database parkingcar 
mvn flyway:migrate -Dflyway.url=jdbc:postgresql://localhost:5432/parkingcar -Dflyway.user=usercar -Dflyway.password=usercar
mvn spring-boot:run
````
Очистка БД:
````shell
mvn flyway:clean -Dflyway.user=usercar -Dflyway.password=usercar -Dflyway.url=jdbc:postgresql://localhost:5432/parkingcar
````

