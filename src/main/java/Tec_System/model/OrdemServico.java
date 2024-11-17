package Tec_System.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * Classe modelo para Ordem de Serviço.
 */
@Data
@Entity
public class OrdemServico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Identificador único da ordem de serviço

    @ManyToOne
    private Cliente cliente; // Cliente associado à ordem de serviço

    private String descricaoProblema;
    private String equipamento;
    private LocalDateTime dataEntrada;
    private LocalDateTime dataSaida;
    private String status;             // Status da ordem (e.g., ABERTA, FINALIZADA)
    private Double valorServico;
    private String observacoes;
}