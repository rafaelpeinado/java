package io.github.rafaelpeinado.service;

import io.github.rafaelpeinado.model.Cliente;
import io.github.rafaelpeinado.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientesService {

//    @Autowired
    private ClientesRepository repository;

//    @Autowired
//    public void setRepository(ClientesRepository repository) {
//        this.repository = repository;
//    }

//    @Autowired
    public ClientesService(ClientesRepository repository) {
        this.repository = repository;
    }

    public void salvarCliente(Cliente cliente) {
        validarCliente(cliente);
        this.repository.persistir(cliente);
//        ClientesRepository clientesRepository = new ClientesRepository();
//        clientesRepository.persistir(cliente);
    }

    public void validarCliente(Cliente cliente) {
        // aplica validações
    }
}
