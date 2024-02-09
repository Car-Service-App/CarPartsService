package ru.vsu.cs.zmaev.carpartsservice.exception;

import org.springframework.http.HttpStatus;

public class NoSuchEntityException extends EntityException {
    private static final String MESSAGE_ID = "No such entity %s with id: %s ";
    private static final String MESSAGE_NAME = "No such entity %s with name: %s ";

    public NoSuchEntityException(Class<?> c, Long id) {
        super(HttpStatus.NOT_FOUND, String.format(MESSAGE_ID, c.getName(), id));
    }

    public NoSuchEntityException(Class<?> c, String name) {
        super(HttpStatus.NOT_FOUND, String.format(MESSAGE_NAME, c.getName(), name));
    }

    public NoSuchEntityException(String message) {
        super(HttpStatus.NOT_FOUND, String.format(message));
    }
}
