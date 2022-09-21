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

Борьба с git
````shell
git remote add origin https://github.com/cherepakhin/parking.git
git push -u origin master
````

## НЕ СДЕЛАНО
1. Похоже нет нормальных логов
2. Интеграционное тестирование с помощью behave
3. Нагрузочное тестирование с помощью yandex-tank
4. Докеризация
