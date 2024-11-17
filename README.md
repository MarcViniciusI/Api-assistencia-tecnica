# Estrutura básica para a API REST de assistência técnica. 
Aqui está um resumo do que foi implementado:

## Estrutura do Projeto:

Configuração do Maven com as dependências necessárias.

Configuração do banco de dados H2.

Estrutura de pacotes organizada.

```mermaid
classDiagram
    class AssistanceServiceApplication {
        <<Application>>
        +main(String[] args)
    }

    class Cliente {
        +Long id
        +String nome
        +String cpf
        +String telefone
        +String email
        +String endereco
    }

    class OrdemServico {
        +Long id
        +String descricaoProblema
        +String equipamento
        +LocalDateTime dataEntrada
        +LocalDateTime dataSaida
        +String status
        +Double valorServico
        +String observacoes
        +Cliente cliente
    }

    class ClienteRepository {
        <<interface>>
        +Cliente findByCpf(String cpf)
    }

    class OrdemServicoRepository {
        <<interface>>
        +List~OrdemServico~ findByClienteId(Long clienteId)
    }

    class ClienteController {
        +criarCliente(Cliente cliente) : Cliente
        +listarClientes() : List~Cliente~
        +buscarCliente(Long id) : ResponseEntity~Cliente~
        +atualizarCliente(Long id, Cliente cliente) : ResponseEntity~Cliente~
        +deletarCliente(Long id) : ResponseEntity~Void~
    }

    class OrdemServicoController {
        +criarOrdemServico(OrdemServico ordemServico) : OrdemServico
        +listarOrdensServico() : List~OrdemServico~
        +buscarOrdemServico(Long id) : ResponseEntity~OrdemServico~
        +buscarOrdensPorCliente(Long clienteId) : List~OrdemServico~
        +atualizarOrdemServico(Long id, OrdemServico ordemServico) : ResponseEntity~OrdemServico~
        +finalizarOrdemServico(Long id) : ResponseEntity~OrdemServico~
        +deletarOrdemServico(Long id) : ResponseEntity~Void~
    }

    ClienteRepository <|-- JpaRepository : extends
    OrdemServicoRepository <|-- JpaRepository : extends
    ClienteController --> ClienteRepository : uses
    OrdemServicoController --> OrdemServicoRepository : uses
    OrdemServico --> Cliente : association

    JpaRepository <|.. ClienteRepository : implements
    JpaRepository <|.. OrdemServicoRepository : implements
```


### Entidades:

Cliente (id, nome, cpf, telefone, email, endereco)

OrdemServico (id, cliente, descricaoProblema, equipamento, datas, status, valor, observacoes)


### Repositories:

ClienteRepository com busca por CPF

OrdemServicoRepository com busca por cliente


### Controllers:

ClienteController com CRUD completo

OrdemServicoController com CRUD e funcionalidades específicas


Endpoints principais:

POST /api/clientes - Criar cliente

GET /api/clientes - Listar clientes

GET /api/clientes/{id} - Buscar cliente específico

PUT /api/clientes/{id} - Atualizar cliente

DELETE /api/clientes/{id} - Deletar cliente

POST /api/ordens-servico - Criar ordem de serviço

GET /api/ordens-servico - Listar ordens

GET /api/ordens-servico/{id} - Buscar ordem específica

PUT /api/ordens-servico/{id} - Atualizar ordem

PUT /api/ordens-servico/{id}/finalizar - Finalizar ordem

GET /api/ordens-servico/cliente/{clienteId} - Buscar ordens por cliente

### Para executar o projeto:

Importe em sua IDE como projeto Maven

Execute a classe AssistanceServiceApplication

Acesse o Swagger UI em: http://localhost:8080/swagger-ui.html

Console do H2 em: http://localhost:8080/h2-console
