## Простой проект "Машины на парковке"

Несколько парковок. Машины занимают места, выезжают. Кол-во мест ограниечено.

## Примечания

Инициализация БД:
````shell
create database parkingcar 
mvn flyway:migrate -Dflyway.url=jdbc:postgresql://localhost:5432/parking_car -Dflyway.user=usercar -Dflyway.password=usercar
mvn spring-boot:run
````
Очистка БД:
````shell
mvn flyway:clean -Dflyway.user=usercar -Dflyway.password=usercar -Dflyway.url=jdbc:postgresql://localhost:5432/parking_car
````

