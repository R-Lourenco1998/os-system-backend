package com.ricardo.os.services;

import com.ricardo.os.domain.Cliente;
import com.ricardo.os.domain.Pessoa;
import com.ricardo.os.dtos.ClienteDTO;
import com.ricardo.os.repositories.ClienteRepository;
import com.ricardo.os.repositories.PessoaRepository;
import com.ricardo.os.services.exceptions.DataIntegrityViolationException;
import com.ricardo.os.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Cliente findById(Integer id) {
        Optional<Cliente> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! " +
                "Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }

    public List<Cliente> findAll() {
        return repository.findAll();
    }

    public Cliente create(ClienteDTO objDTO) {
        if (findByCPF(objDTO) != null) {
            throw new DataIntegrityViolationException("CPF já cadastrado!");
        }
        return repository.save(new Cliente(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone()));
    }

    public Cliente update(Integer id, ClienteDTO objDTO) {
        Cliente oldObj = findById(id);
        if (findByCPF(objDTO) != null && findByCPF(objDTO).getId() != id) {
            throw new DataIntegrityViolationException("CPF já cadastrado!");
        }
        oldObj.setNome(objDTO.getNome());
        oldObj.setCpf(objDTO.getCpf());
        oldObj.setTelefone(objDTO.getTelefone());
        return repository.save(oldObj);
    }

    private Pessoa findByCPF(ClienteDTO objDTO) {
        Pessoa obj = pessoaRepository.findByCPF(objDTO.getCpf());
        if (obj != null) {
            throw new ObjectNotFoundException("CPF já cadastrado!");
        }
        return obj;
    }

    public void delete(Integer id) {
        Cliente obj = findById(id);
        if (obj.getList().size() > 0) {
            throw new DataIntegrityViolationException("Cliente possui ordens de serviço, não pode ser deletado!");
        }
        repository.deleteById(id);
    }
}
