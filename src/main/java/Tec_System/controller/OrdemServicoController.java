package Tec_System.controller;

import Tec_System.model.OrdemServico;
import Tec_System.repository.OrdemServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Controlador REST para a entidade OrdemServico.
 * Gerencia operações de criação, leitura, atualização e exclusão.
 */
@RestController
@RequestMapping("/api/ordens-servico")
public class OrdemServicoController {

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    @PostMapping
    public OrdemServico criarOrdemServico(@RequestBody OrdemServico ordemServico) {
        // Cria uma nova ordem de serviço
        ordemServico.setDataEntrada(LocalDateTime.now());
        ordemServico.setStatus("ABERTA");
        return ordemServicoRepository.save(ordemServico);
    }

    @GetMapping
    public List<OrdemServico> listarOrdensServico() {
        // Lista todas as ordens de serviço
        return ordemServicoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdemServico> buscarOrdemServico(@PathVariable Long id) {
        // Busca uma ordem de serviço pelo ID
        return ordemServicoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cliente/{clienteId}")
    public List<OrdemServico> buscarOrdensPorCliente(@PathVariable Long clienteId) {
        // Busca ordens de serviço associadas a um cliente específico
        return ordemServicoRepository.findByClienteId(clienteId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrdemServico> atualizarOrdemServico(@PathVariable Long id, @RequestBody OrdemServico ordemServico) {
        // Atualiza as informações de uma ordem de serviço
        if (!ordemServicoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        ordemServico.setId(id);
        return ResponseEntity.ok(ordemServicoRepository.save(ordemServico));
    }

    @PutMapping("/{id}/finalizar")
    public ResponseEntity<OrdemServico> finalizarOrdemServico(@PathVariable Long id) {
        // Finaliza uma ordem de serviço existente
        return ordemServicoRepository.findById(id)
                .map(ordem -> {
                    ordem.setStatus("FINALIZADA");
                    ordem.setDataSaida(LocalDateTime.now());
                    return ResponseEntity.ok(ordemServicoRepository.save(ordem));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarOrdemServico(@PathVariable Long id) {
        // Exclui uma ordem de serviço pelo ID
        if (!ordemServicoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        ordemServicoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
