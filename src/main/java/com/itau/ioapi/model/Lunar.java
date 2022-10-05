package com.itau.ioapi.model;

import com.itau.ioapi.exceptions.SignoLunarNaoEncontradoException;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public enum Lunar {
    CASIMIRO(ZoneId.of("America/Recife"), LocalTime.of(12, 1), LocalTime.of(23, 59)),
    ODIN(ZoneId.of("America/Cuiaba"), LocalTime.of(0, 0), LocalTime.of(12, 0)),
    GANDALF(ZoneId.of("America/Sao_Paulo"), LocalTime.of(0, 0), LocalTime.of(23, 59));

    private ZoneId zoneId;
    private LocalTime initTime;
    private LocalTime lastTime;

    Lunar(ZoneId zoneId, LocalTime initTime, LocalTime lastTime) {
        this.zoneId = zoneId;
        this.initTime = initTime;
        this.lastTime = lastTime;
    }

    public ZoneId getZoneId() {
        return zoneId;
    }

    public LocalTime getInitTime() {
        return initTime;
    }

    public LocalTime getLastTime() {
        return lastTime;
    }

    public static Lunar getLunar(ZonedDateTime zonedDateTime) {
        ZoneId nascimentoZoneId = zonedDateTime.getZone();
        LocalTime nascimentoLocalTime = zonedDateTime.toLocalTime();

        for (Lunar lunar : Lunar.values()) {
            if (nascimentoZoneId.equals(lunar.getZoneId())) {
                if (!((nascimentoLocalTime.isAfter(lunar.getLastTime())) || nascimentoLocalTime.isBefore(lunar.getInitTime()))) {
                    return lunar;
                }
            }
        }

        throw new SignoLunarNaoEncontradoException("Signo Lunar Não Encontrado - Em Construção");
    }
}
