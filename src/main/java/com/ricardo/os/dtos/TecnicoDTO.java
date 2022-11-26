package com.ricardo.os.dtos;

import com.ricardo.os.domain.Tecnico;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Getter
@Setter
public class TecnicoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

        private Integer id;
        @NotEmpty(message = "O campo NOME é requerido")
        private String nome;

        @CPF
        @NotEmpty(message = "O campo CPF é requerido")
        private String cpf;
        @NotEmpty(message = "O campo TELEFONE é requerido")
        private String telefone;

    public TecnicoDTO() {
        super();
    }

    public TecnicoDTO(Tecnico obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.cpf = obj.getCpf();
        this.telefone = obj.getTelefone();
    }
}
