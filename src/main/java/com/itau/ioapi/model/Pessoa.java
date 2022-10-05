package com.itau.ioapi.model;

import com.itau.ioapi.exceptions.SignoLunarNaoEncontradoException;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.*;

@Data
@AllArgsConstructor
public class Pessoa {
    private String nome;
    private ZoneId local;
    private LocalDateTime nascimento;

    public String getPrimeiroNomeMaisculo() {
        return nome.split(" ")[0].toUpperCase();
    }

    public int getIdadeAtual() {
        Period period = Period.between(nascimento.toLocalDate(), LocalDate.now());
        return period.getYears();
    }

    public String getSigno() {
        return Signos.getSigno(MonthDay.of(nascimento.getMonth(), nascimento.getDayOfMonth())).toString();
    }

    public String getAscendente() {
        return Ascendente.getAscendente(nascimento.toLocalTime()).toString();
    }

    public String getSignoLunar() {
        try {
            return Lunar.getLunar(ZonedDateTime.of(nascimento, local)).toString();
        } catch (SignoLunarNaoEncontradoException exception) {
            return exception.getLocalizedMessage();
        }
    }

    public boolean getAnoBissexto() {
        return Year.isLeap(nascimento.getYear());
    }

}
