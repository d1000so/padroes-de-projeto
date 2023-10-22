package edu.dio.consultacep.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.dio.consultacep.model.Cliente;
import edu.dio.consultacep.model.ClienteRepository;
import edu.dio.consultacep.model.Endereco;
import edu.dio.consultacep.model.EnderecoRepository;
import edu.dio.consultacep.service.ClienteService;
import edu.dio.consultacep.service.ViaCepService;

/**
 * Esta é a implementação da estratégia de serviço {@link ClienteService} que pode
 * ser injetada pelo Spring (usando {@link Autowired}). Como uma classe anotada com
 * {@link Service}, ela é tratada como um Singleton.
 *
 * Essa classe implementa o padrão de projeto <b>Strategy</b>, fornecendo uma estratégia
 * para realizar operações relacionadas aos clientes. Ela também age como uma
 * <b>Facade</b> para abstrair as integrações com subsistemas, simplificando a
 * interação com os dados do cliente e informações de endereço.
 *
 * @author d1000so
 */
@Service
public class ClienteServiceImpl implements ClienteService {

    // Singleton: Injetar os componentes do Spring com @Autowired.
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ViaCepService viaCepService;

    // Strategy: Implementar os métodos definidos na interface.
    // Facade: Abstrair integrações com subsistemas, provendo uma interface simples.

    @Override
    public Iterable<Cliente> buscarTodos() {
        // Buscar todos os Clientes.
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) {
        // Buscar Cliente por ID.
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.get();
    }

    @Override
    public void inserir(Cliente cliente) {
        salvarClienteComCep(cliente);
    }

    @Override
    public void atualizar(Long id, Cliente cliente) {
        // Buscar Cliente por ID, caso exista:
        Optional<Cliente> clienteBd = clienteRepository.findById(id);
        if (clienteBd.isPresent()) {
            salvarClienteComCep(cliente);
        }
    }

    @Override
    public void deletar(Long id) {
        // Deletar Cliente por ID.
        clienteRepository.deleteById(id);
    }

    private void salvarClienteComCep(Cliente cliente) {
        // Verificar se o Endereco do Cliente já existe (pelo CEP).
        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
            // Caso não exista, integrar com o ViaCEP e persistir o retorno.
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
        cliente.setEndereco(endereco);
        // Inserir Cliente, vinculando o Endereco (novo ou existente).
        clienteRepository.save(cliente);
    }

}
