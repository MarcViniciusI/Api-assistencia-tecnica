package Tec_System.repository;

import Tec_System.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório para a entidade Cliente.
 * Fornece operações CRUD automáticas.
 */
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Cliente findByCpf(String cpf); // Busca cliente pelo CPF
}
