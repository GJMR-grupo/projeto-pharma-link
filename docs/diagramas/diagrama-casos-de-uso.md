# Diagrama de Casos de Uso --- PharmaLink

**Aula 08 --- Design de Software**\
**Funcionalidade:** Consultar disponibilidade de medicamento em estoque.

## Objetivo

Representar os atores envolvidos e os objetivos relacionados à consulta
de disponibilidade de medicamentos.

``` mermaid
flowchart LR
    C[Cliente]
    F[Farmacêutico]

    subgraph PharmaLink
        UC1([Consultar medicamento])
        UC2([Verificar disponibilidade])
        UC3([Visualizar quantidade disponível])
        UC4([Receber aviso de indisponibilidade])
    end

    C --> UC1
    F --> UC1
    UC1 --> UC2
    UC2 --> UC3
    UC2 --> UC4
```

## Descrição

O **Cliente** ou **Farmacêutico** inicia a consulta de um medicamento. O
sistema verifica sua disponibilidade no estoque e apresenta a quantidade
disponível. Caso não exista estoque, o sistema informa a
indisponibilidade.
