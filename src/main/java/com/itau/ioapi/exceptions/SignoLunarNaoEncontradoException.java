package com.itau.ioapi.exceptions;

public class SignoLunarNaoEncontradoException extends RuntimeException {
    public SignoLunarNaoEncontradoException() {
        this("Signo Lunar não encontrado");
    }

    public SignoLunarNaoEncontradoException(String message) {
        super(message);
    }
}
