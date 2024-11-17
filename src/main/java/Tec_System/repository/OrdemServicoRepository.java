package Tec_System.repository;

import Tec_System.model.OrdemServico;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * Repositório para a entidade OrdemServico.
 * Fornece operações CRUD automáticas.
 */
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long> {
    List<OrdemServico> findByClienteId(Long clienteId); // Busca ordens de serviço por ID do cliente
}
