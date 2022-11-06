package com.ricardo.os.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Prioridade {

    BAIXA(0, "BAIXA"),
    MEDIA(1, "MEDIA"),
    ALTA(2, "ALTA");

    private int cod;
    private String descricao;

    public static Prioridade toEnum(Integer cod){
        if (cod == null) {
            return null;
        }

        for (Prioridade x : Prioridade.values()) {
            if (cod.equals(x.getCod())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Prioridade inválida: " + cod);
    }
}
