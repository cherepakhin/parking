## Простой проект "Машины на парковке"
В проекте показаны типичные элементы и приемы spring-boot приложения. Добавлено несколько, как мне кажется, полезные приемы и инструменты.

##Постановка
Несколько парковок. Машины занимают места на конкретной парковке, выезжают. Кол-во мест на парковке ограничено.

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
(в нормальном проекте master для разработчиков недоступен)

## Тестирование с помощью утилиты [httpie](https://httpie.io/)
````shell
$ http 192.168.1.20:8090/car/

HTTP/1.1 200 
Connection: keep-alive
Content-Type: application/json
Date: Thu, 29 Dec 2022 08:38:58 GMT
Keep-Alive: timeout=60
Transfer-Encoding: chunked

[
    {
        "gosNumber": "111",
        "id": 1,
        "model": "mark1"
    },
    {
        "gosNumber": "222",
        "id": 2,
        "model": "mark2"
    }
]
````

## НЕ СДЕЛАНО
1. Интеграционное тестирование с помощью behave
2. Нагрузочное тестирование с помощью yandex-tank
3. Докеризация
4. Описать генерацию документации java-doc
5. И пользовательской док-ии на rest
