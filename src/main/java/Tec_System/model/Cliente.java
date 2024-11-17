package Tec_System.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * Classe modelo para Cliente.
 */
@Data
@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Identificador único do cliente

    private String nome;    // Nome do cliente
    private String cpf;     // CPF do cliente
    private String telefone; // Telefone do cliente
    private String email;    // Email do cliente
    private String endereco; // Endereço do cliente
}
