package ru.perm.v.parking.controller.exception;

public interface ErrorMessaage {
    String CAR_NOT_FOUND ="Не найдена машина с id=%d";
    String CAR_OTHER_ERROR ="Какая-то другая ошибка при получении машины с id=%d";
}
