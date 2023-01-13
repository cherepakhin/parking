package ru.perm.v.parking.controller.exception;

/**
 * Словарь сообщений об ошибках при работе с машинами
 */
public interface CarErrorMessage {
    String CAR_NOT_FOUND ="Не найдена машина с id=%d";
    String CAR_OTHER_ERROR ="Ошибка при получении машины с id=%d, exception.message=%s";
}
