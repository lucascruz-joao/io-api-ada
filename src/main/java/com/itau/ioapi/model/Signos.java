package com.itau.ioapi.model;

import com.itau.ioapi.exceptions.SignoNaoEncontradoException;

import java.time.MonthDay;
import java.util.Arrays;

public enum Signos {
    ARIES(MonthDay.of(3, 21), MonthDay.of(4, 20)),
    TOURO(MonthDay.of(4, 21), MonthDay.of(5, 20)),
    GEMEOS(MonthDay.of(5, 21), MonthDay.of(6, 20)),
    CANCER(MonthDay.of(6, 21), MonthDay.of(7, 22)),
    LEAO(MonthDay.of(7, 23), MonthDay.of(8, 22)),
    VIRGEM(MonthDay.of(8, 23), MonthDay.of(9, 22)),
    LIBRA(MonthDay.of(9, 23), MonthDay.of(10, 22)),
    ESCORPIAO(MonthDay.of(10, 23), MonthDay.of(11, 21)),
    SAGITARIO(MonthDay.of(11, 22), MonthDay.of(12, 21)),
    CAPRICORNIO(MonthDay.of(12, 22), MonthDay.of(1, 19)),
    AQUARIO(MonthDay.of(1, 20), MonthDay.of(2, 18)),
    PEIXES(MonthDay.of(2, 19), MonthDay.of(3, 20));

    private final MonthDay initDate;
    private final MonthDay lastDate;

    Signos(MonthDay initDate, MonthDay lastDate) {
        this.initDate = initDate;
        this.lastDate = lastDate;
    }

    public MonthDay getInitDate() {
        return initDate;
    }

    public MonthDay getLastDate() {
        return lastDate;
    }

    public static Signos getSigno(MonthDay birthday) {
        return Arrays.stream(Signos.values()).filter(signo -> {
            if (signo.equals(Signos.CAPRICORNIO)) {
                return (!birthday.isBefore(signo.getInitDate()) || !birthday.isAfter(signo.getLastDate()));
            } else {
                return !(birthday.isBefore(signo.getInitDate()) || birthday.isAfter(signo.getLastDate()));
            }
        }).findFirst().orElseThrow(SignoNaoEncontradoException::new);
    }

}
