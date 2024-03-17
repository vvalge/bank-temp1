package com.bank.antifraud.util;
/**
 * Исключение, выбрасываемое при попытке доступа к операции трансфера, который не существует
 */
public class TransferNotFound extends RuntimeException {
    public TransferNotFound(String exception) {
        super(exception);
    }
}
