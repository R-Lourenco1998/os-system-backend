package com.ricardo.os.services;

import com.ricardo.os.domain.Pessoa;
import com.ricardo.os.domain.Tecnico;
import com.ricardo.os.dtos.TecnicoDTO;
import com.ricardo.os.repositories.PessoaRepository;
import com.ricardo.os.repositories.TecnicoRepository;
import com.ricardo.os.services.exceptions.DataIntegrityViolationException;
import com.ricardo.os.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository repository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Tecnico findById(Integer id) {
        Optional<Tecnico> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! " +
                "Id: " + id + ", Tipo: " + Tecnico.class.getName()));
    }

    public List<Tecnico> findAll() {
        return repository.findAll();
    }

    public Tecnico create(TecnicoDTO objDTO) {
        if (findByCPF(objDTO) != null) {
            throw new DataIntegrityViolationException("CPF já cadastrado!");
        }
        return repository.save(new Tecnico(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone()));
    }

    public Tecnico update(Integer id, TecnicoDTO objDTO) {
        Tecnico oldObj = findById(id);
        if (findByCPF(objDTO) != null && findByCPF(objDTO).getId() != id) {
            throw new DataIntegrityViolationException("CPF já cadastrado!");
        }
        oldObj.setNome(objDTO.getNome());
        oldObj.setCpf(objDTO.getCpf());
        oldObj.setTelefone(objDTO.getTelefone());
        return repository.save(oldObj);
    }

    private Pessoa findByCPF(TecnicoDTO objDTO) {
        Pessoa obj = pessoaRepository.findByCPF(objDTO.getCpf());
        if (obj != null) {
            throw new ObjectNotFoundException("CPF já cadastrado!");
        }
        return obj;
    }

    public void delete(Integer id) {
        Tecnico obj = findById(id);
        if (obj.getList().size() > 0) {
            throw new DataIntegrityViolationException("Técnico possui ordens de serviço, não pode ser deletado!");
        }
        repository.deleteById(id);
    }
}
