package com.example.persistence.exception;

/**
 * DAOでDELETEが失敗した際にスローされる例外です。
 * @author tada
 */
public class DeleteFailureException extends RuntimeException {

    public DeleteFailureException() {
    }

    public DeleteFailureException(String msg) {
        super(msg);
    }
}
