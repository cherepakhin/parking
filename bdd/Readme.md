## Behave тестирование (нужен python!!!)

Установить<br/>
https://github.com/behave/behave <br/>
https://github.com/behave-restful/behave-restful <br/>
```shell
pip install behave
pip install behave-restful
```

behave-restful пакет, ктр помогает делать REST запросы и парсить, анализировать ответы

Для проверки в ./bdd выполнить
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

## Отчеты Allure

Установка формата Allure
```shell
pip install allure-behave
```
Для выполнения тестов и выдачи отчетов в красивом формате в папке ./bdd выполнить:
````shell
behave -f allure_behave.formatter:AllureFormatter -o reports
````
Для просмотра отчетов в папке ./bdd выполнить
````shell
allure  serve reports/
````