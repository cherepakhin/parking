# -- FILE: features/steps/example_steps.py
from behave import given, when, then, step

@given('we have behave installed')
def step_impl(context):
    pass

@when('we implement {number:d} tests')
def step_impl(context, number):  # -- NOTE: number is converted into integer
    assert number > 1 or number == 3
    context.tests_count = number + 1 # Установка переменной контекста tests_count. Будет проверено в след.тесте

@then('behave will test them for us!')
def step_impl(context):
    assert context.failed is False
    assert context.tests_count == 4