package com.ricardo.os.services;

import com.ricardo.os.domain.Cliente;
import com.ricardo.os.domain.OS;
import com.ricardo.os.domain.Tecnico;
import com.ricardo.os.domain.enums.Prioridade;
import com.ricardo.os.domain.enums.Status;
import com.ricardo.os.dtos.OSDTO;
import com.ricardo.os.repositories.OSRepository;
import com.ricardo.os.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class OsService {

    @Autowired
    private OSRepository repository;

    @Autowired
    private TecnicoService tecnicoService;

    @Autowired
    private ClienteService clienteService;

    public OS findById(Integer id) {
        Optional<OS> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + OS.class.getName()));
    }

    public List<OS> findAll() {
        return repository.findAll();
    }

    public OS create(@Valid OSDTO obj) {
        return fromDTO(obj);

    }

    private OS fromDTO(OSDTO obj) {
        OS newObj = new OS();
        newObj.setId(obj.getId());
        newObj.setObservacoes(obj.getObservacoes());
        newObj.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
        newObj.setStatus(Status.toEnum(obj.getStatus()));

        Tecnico tec = tecnicoService.findById(obj.getTecnico());
        Cliente cli = clienteService.findById(obj.getCliente());

        newObj.setTecnico(tec);
        newObj.setCliente(cli);
        return repository.save(newObj);
    }
}
