## Простой проект "Машины на парковке"
В проекте показаны типичные элементы и приемы spring-boot приложения. Добавлено несколько, как мне кажется, полезные приемы и инструменты.

##Постановка
Несколько парковок. Машины занимают места на конкретной парковке, выезжают. Кол-во мест на парковке ограничено.

## Работа СУБД
Использован пакет наката изменений [https://flywaydb.org/](https://flywaydb.org/) 
Инициализация БД:
````shell
create database parkingcar 
mvn flyway:migrate -Dflyway.url=jdbc:postgresql://localhost:5432/parkingcar -Dflyway.user=usercar -Dflyway.password=usercar
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
## Spring Actuator
Реализуется добавлением maven зависимости
````
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>

````
Доступен по [http://127.0.0.1:8080/actuator](http://127.0.0.1:8080/actuator)
````json
{
    "_links": {
        "health": {
            "href": "http://127.0.0.1:8090/actuator/health",
            "templated": false
        },
        "health-path": {
            "href": "http://127.0.0.1:8090/actuator/health/{*path}",
            "templated": true
        },
        "info": {
            "href": "http://127.0.0.1:8090/actuator/info",
            "templated": false
        },
        "self": {
            "href": "http://127.0.0.1:8090/actuator",
            "templated": false
        }
    }
}
````
## Api-docs
Доступен [http://127.0.0.1:8080/v3/api-docs](http://127.0.0.1:8080/v3/api-docs)
## Swagger-ui
[http://127.0.0.1:8080/swagger-ui/index.html](http://127.0.0.1:8080/swagger-ui/index.html)
## Ручное тестирование с помощью утилиты [httpie](https://httpie.io/)
````shell
$ http :8080/car/
(или http 127.0.0.1:8080/car/)

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

## Behave тестирование (нужен python!!!)
Установить<br/>
https://github.com/behave/behave <br/>
https://github.com/behave-restful/behave-restful <br/>
```shell
pip install behave
pip install behave-restful
```
Пакеты behave, behave-restful помогают делать REST запросы, парсить и анализировать ответы. 
Для проверки работоспособности в папке <b>./bdd</b> выполнить
````shell
behave
````
Будет выдано примерно следующее:
````shell
Feature: Showing off behave # features/example.feature:2

  Scenario: Run a simple test          # features/example.feature:4
    Given we have behave installed     # features/steps/example_steps.py:4 0.000s
    When we implement 5 tests          # features/steps/example_steps.py:8 0.000s
    Then behave will test them for us! # features/steps/example_steps.py:13 0.000s

1 feature passed, 0 failed, 0 skipped
1 scenario passed, 0 failed, 0 skipped
3 steps passed, 0 failed, 0 skipped, 0 undefined
Took 0m0.000s
````

##Отчеты Allure
Allure нужен для красивого вывода результатов тестов. Установка формата Allure: 
```shell
pip install allure-behave
```
Для выполнения тестов и выдачи отчетов в формате Allure в папке ./bdd выполнить:
````shell
behave -f allure_behave.formatter:AllureFormatter -o reports
````
отчеты будут в папке bdd/reports/ 
Для просмотра отчетов в папке ./bdd выполнить
````shell
allure serve reports/
````
## НЕ СДЕЛАНО
1.  ~~Интеграционное тестирование с помощью behave~~
2.  Нагрузочное тестирование с помощью yandex-tank
3.  Докеризация
4.  Описать генерацию документации java-doc
5. `~~И пользовательской док-ии на rest в OpenApi~~
6.  Security
7.  jasperreports (???)
8.  error-controller
9.  Покрытие тестами
10. ~~Настройка логов~~

