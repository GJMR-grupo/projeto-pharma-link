# Diagrama de Classes --- PharmaLink

**Aula 08 --- Design de Software**\
**Funcionalidade:** Consultar disponibilidade de medicamento em estoque.

``` mermaid
classDiagram
    class Medicamento {
        -Long id
        -String nome
        -String principioAtivo
        +consultarDados()
    }

    class Estoque {
        -Long id
        -int quantidade
        +verificarDisponibilidade() boolean
        +consultarQuantidade() int
    }

    class StockService {
        +consultarDisponibilidade(Long medicamentoId)
    }

    class MedicamentoRepository {
        +buscarPorId(Long id)
    }

    class EstoqueRepository {
        +buscarPorMedicamento(Long medicamentoId)
    }

    Medicamento "1" --> "1" Estoque : possui
    StockService --> MedicamentoRepository : consulta
    StockService --> EstoqueRepository : consulta
    EstoqueRepository --> Estoque : retorna
    MedicamentoRepository --> Medicamento : retorna
```

## Responsabilidades

-   **Medicamento:** representa os dados do medicamento.
-   **Estoque:** representa a quantidade disponível e a situação do
    item.
-   **StockService:** concentra a regra da consulta de disponibilidade.
-   **MedicamentoRepository:** localiza o medicamento.
-   **EstoqueRepository:** recupera as informações de estoque.
