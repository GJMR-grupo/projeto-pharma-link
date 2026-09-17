# Diagrama de Sequência --- PharmaLink

**Aula 08 --- Design de Software**\
**Funcionalidade:** Consultar disponibilidade de medicamento em estoque.

``` mermaid
sequenceDiagram
    actor Usuario
    participant Interface
    participant StockService
    participant MedicamentoRepository
    participant EstoqueRepository

    Usuario->>Interface: informa medicamento
    Interface->>StockService: consultarDisponibilidade(medicamentoId)
    StockService->>MedicamentoRepository: buscarPorId(medicamentoId)
    MedicamentoRepository-->>StockService: medicamento

    alt medicamento encontrado
        StockService->>EstoqueRepository: buscarPorMedicamento(medicamentoId)
        EstoqueRepository-->>StockService: estoque
        alt quantidade maior que zero
            StockService-->>Interface: medicamento disponível + quantidade
            Interface-->>Usuario: exibe disponibilidade
        else sem estoque
            StockService-->>Interface: medicamento indisponível
            Interface-->>Usuario: exibe aviso de indisponibilidade
        end
    else medicamento não encontrado
        StockService-->>Interface: medicamento não encontrado
        Interface-->>Usuario: exibe mensagem de erro
    end
```

## Fluxo principal

O usuário informa o medicamento, o sistema localiza o cadastro, consulta
o estoque e apresenta a disponibilidade e a quantidade.

## Fluxos alternativos

Caso o medicamento não exista, é exibida uma mensagem de erro. Caso
exista, mas a quantidade seja zero, o sistema informa que está
indisponível.
