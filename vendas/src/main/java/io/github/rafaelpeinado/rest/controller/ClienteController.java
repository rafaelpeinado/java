package io.github.rafaelpeinado.rest.controller;

import io.github.rafaelpeinado.domain.entity.Cliente;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/clientes")
public class ClienteController {

    @RequestMapping(
            value = {"/hello/{nome}", "/{nome}"},
            method = RequestMethod.GET,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"}
    )
    @ResponseBody
    public String helloClientes(@PathVariable("nome") String nomeCliente) {
        return String.format("Hello %s", nomeCliente);
    }

    @RequestMapping(
            value = {"/hello/{nome}", "/{nome}"},
            method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"}
    )
    @ResponseBody
    public Cliente helloClientes2(@PathVariable("nome") String nomeCliente, @RequestBody Cliente cliente) {
        return cliente;
    }
}
