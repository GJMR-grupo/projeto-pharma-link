# Diagrama de Atividades --- PharmaLink

**Aula 08 --- Design de Software**\
**Processo:** Consulta de disponibilidade de medicamento.

``` mermaid
flowchart TD
    A([Início]) --> B[Usuário informa o medicamento]
    B --> C[Sistema recebe a consulta]
    C --> D{Medicamento existe?}

    D -- Não --> E[Exibir: medicamento não encontrado]
    E --> Z([Fim])

    D -- Sim --> F[Consultar estoque]
    F --> G{Quantidade maior que zero?}

    G -- Sim --> H[Exibir medicamento disponível]
    H --> I[Exibir quantidade em estoque]
    I --> Z

    G -- Não --> J[Exibir medicamento indisponível]
    J --> Z
```

## Resultado esperado

Ao final do processo, o usuário recebe uma resposta clara sobre a
disponibilidade do medicamento, incluindo a quantidade quando houver
estoque.
