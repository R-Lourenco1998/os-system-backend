package com.ricardo.os.dtos;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ricardo.os.domain.OS;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class OSDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataAbertura;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataFechamento;
    private Integer prioridade;
    @NotBlank(message = "O campo OBSERVAÇÕES é requerido")
    private String observacoes;
    private Integer status;

    private Integer tecnico;

    private Integer cliente;

    public OSDTO() {
        super();
    }

    public OSDTO(OS obj) {
        this.id = obj.getId();
        this.dataAbertura = obj.getDataAbertura();
        this.dataFechamento = obj.getDataFechamento();
        this.prioridade = obj.getPrioridade().getCod();
        this.observacoes = obj.getObservacoes();
        this.status = obj.getStatus().getCod();
        this.tecnico = obj.getTecnico().getId();
        this.cliente = obj.getCliente().getId();
    }
}
