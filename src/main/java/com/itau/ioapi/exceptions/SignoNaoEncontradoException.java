package com.itau.ioapi.exceptions;

public class SignoNaoEncontradoException extends RuntimeException {
    public SignoNaoEncontradoException() {
        this("Signo não encontrado");
    }

    public SignoNaoEncontradoException(String message) {
        super(message);
    }
}
