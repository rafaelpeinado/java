package io.github.rafaelpeinado.rest.controller;

import io.github.rafaelpeinado.domain.entity.Cliente;
import io.github.rafaelpeinado.domain.repository.Clientes;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/api/clientes")
public class ClienteController {

    private Clientes clientes;

    public ClienteController(Clientes clientes) {
        this.clientes = clientes;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Cliente> getClienteById(@PathVariable Integer id) {
        Optional<Cliente> cliente = clientes.findById(id);
        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("")
    @ResponseBody
    public ResponseEntity<Cliente> save(@RequestBody Cliente cliente) {
        return ResponseEntity.ok(clientes.save(cliente));
    }
}
