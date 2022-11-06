package com.ricardo.os.services;

import com.ricardo.os.domain.Cliente;
import com.ricardo.os.domain.OS;
import com.ricardo.os.domain.Tecnico;
import com.ricardo.os.domain.enums.Prioridade;
import com.ricardo.os.domain.enums.Status;
import com.ricardo.os.repositories.ClienteRepository;
import com.ricardo.os.repositories.OSRepository;
import com.ricardo.os.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private OSRepository osRepository;
    public void instanciaDB() {
        Tecnico t1 = new Tecnico(null, "Ricardo", "132.860.274-56", "(81) 9 8907-7229");
        Cliente c1 = new Cliente(null, "Maria", "028.594.764-82", "(81) 9 5401-7229");
        OS os1 = new OS(null, null, Prioridade.ALTA, "teste create OP", Status.ANDAMENTO, t1, c1);

        t1.getList().add(os1);
        c1.getList().add(os1);

        tecnicoRepository.saveAll(Arrays.asList(t1));
        clienteRepository.saveAll(Arrays.asList(c1));
        osRepository.saveAll(Arrays.asList(os1));
    }
}
