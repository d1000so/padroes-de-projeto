package edu.dio.consultacep.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.dio.consultacep.service.ClienteService;
import edu.dio.consultacep.model.Cliente;

/**
 * Este {@link RestController} é responsável por manipular as operações relacionadas aos clientes.
 * Implementa a <b>Facade</b> para gerir a interação com clientes.
 *
 * @author d1000so
 */
@RestController
@RequestMapping("clientes")
public class ClienteRestController {

    @Autowired
    private ClienteService clienteService;

    /**
     * Recupera todos os clientes.
     *
     * @return Um {@link ResponseEntity} contendo uma coleção de {@link Cliente}.
     */
    @GetMapping
    public ResponseEntity<Iterable<Cliente>> buscarTodos() {
        return ResponseEntity.ok(clienteService.buscarTodos());
    }

    /**
     * Busca um cliente pelo ID.
     *
     * @param id O ID do cliente a ser buscado.
     * @return Um {@link ResponseEntity} contendo o {@link Cliente} encontrado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.buscarPorId(id));
    }

    /**
     * Insere um novo cliente.
     *
     * @param cliente O {@link Cliente} a ser inserido.
     * @return Um {@link ResponseEntity} contendo o {@link Cliente} inserido.
     */
    @PostMapping
    public ResponseEntity<Cliente> inserir(@RequestBody Cliente cliente) {
        clienteService.inserir(cliente);
        return ResponseEntity.ok(cliente);
    }

    /**
     * Atualiza um cliente existente.
     *
     * @param id O "ID" do cliente a ser atualizado.
     * @param cliente O {@link Cliente} com os dados atualizados.
     * @return Um {@link ResponseEntity} contendo o {@link Cliente} atualizado.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @RequestBody Cliente cliente) {
        clienteService.atualizar(id, cliente);
        return ResponseEntity.ok(cliente);
    }

    /**
     * Deleta um cliente pelo ID.
     *
     * @param id O ID do cliente a ser deletado.
     * @return Um {@link ResponseEntity} indicando a operação de exclusão bem-sucedida.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        clienteService.deletar(id);
        return ResponseEntity.ok().build();
    }
}

