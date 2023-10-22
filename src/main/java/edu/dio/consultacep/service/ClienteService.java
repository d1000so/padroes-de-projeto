package edu.dio.consultacep.service;


import edu.dio.consultacep.model.Cliente;

/**
 * Interface que define o padrão <b>Strategy</b> para operações de gerenciamento de clientes.
 *
 * O padrão Strategy permite a definição de um conjunto de algoritmos, encapsulando cada um deles
 * em uma classe separada que implementa esta interface. Isso permite a troca flexível e dinâmica
 * dos algoritmos de busca, inserção, atualização e exclusão de clientes em tempo de execução.
 *
 * @author d1000so
 */
public interface ClienteService {

    /**
     * Obtém uma lista de todos os clientes.
     *
     * @return Uma coleção de clientes
     */
    Iterable<Cliente> buscarTodos();

    /**
     * Obtém um cliente pelo seu ID.
     *
     * @param id O ID do cliente a ser recuperado
     * @return O cliente encontrado ou null, caso não exista um cliente com o ID especificado
     */
    Cliente buscarPorId(Long id);

    /**
     * Insere um novo cliente no sistema.
     *
     * @param cliente O cliente a ser inserido
     */
    void inserir(Cliente cliente);

    /**
     * Atualiza as informações de um cliente existente pelo seu ID.
     *
     * @param id      O ID do cliente a ser atualizado
     * @param cliente O cliente com as informações atualizadas
     */
    void atualizar(Long id, Cliente cliente);

    /**
     * Deleta um cliente pelo seu ID.
     *
     * @param id O ID do cliente a ser deletado
     */
    void deletar(Long id);
}
