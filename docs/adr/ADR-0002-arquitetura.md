# ADR-0002 — Separação de Responsabilidades do PharmacyApplicationService

## Status

Aceito.

## Data

21/08/2026

## Participantes

- Júllya
- Guilherme Felipe
- Michelly Lima
- Ryan Rodrigues

## Contexto

Durante a Aula 02, a equipe analisou a organização das responsabilidades existentes no código do PharmaLink.

Foi identificado que a classe `PharmacyApplicationService` concentrava diversas responsabilidades do sistema, além da coordenação do fluxo principal da aplicação.

Entre as responsabilidades presentes estavam operações relacionadas a:

- pedidos;
- validação de receita;
- controle e consulta de estoque;
- processamento de pagamento;
- solicitação de entrega;
- envio de notificações.

Essa concentração tornava a classe responsável por diferentes partes do sistema, aumentando seu tamanho e dificultando a compreensão, manutenção e evolução do código.

Além disso, conforme orientação da disciplina, o pacote `patterns` não deve ser alterado nesta etapa. Portanto, a refatoração deve preservar as implementações existentes nesse pacote.

## Decisão

A equipe decidiu refatorar a `PharmacyApplicationService`, separando responsabilidades específicas em novos serviços.

Foram criadas as seguintes classes:

### `PrescriptionService`

Responsável pelas regras relacionadas à necessidade de receita médica para a venda de medicamentos.

### `StockService`

Responsável pelas operações relacionadas ao estoque, incluindo busca de lotes disponíveis, verificação de quantidade, validade e reserva do medicamento.

### `PaymentService`

Responsável pelo processamento do pagamento e pela comunicação necessária com o sistema legado de pagamento.

### `DeliveryService`

Responsável pelas operações relacionadas à entrega e pela comunicação com o serviço externo de entrega.

### `NotificationService`

Responsável pelo envio de notificações relacionadas às atualizações dos pedidos.

Após a refatoração, a `PharmacyApplicationService` permanece responsável por coordenar o fluxo principal da aplicação, porém passa a delegar essas operações para classes com responsabilidades específicas.

Nenhuma classe pertencente ao pacote `patterns` foi modificada.

## Justificativa

A decisão foi tomada para melhorar a separação de responsabilidades do sistema.

Antes da alteração, uma única classe conhecia e executava diferentes operações relacionadas ao funcionamento do PharmaLink.

Com a separação, cada serviço passa a possuir um objetivo mais específico, enquanto a `PharmacyApplicationService` atua principalmente como coordenadora do fluxo.

Essa organização busca aumentar a coesão das classes e reduzir a concentração de responsabilidades.

## Alternativas Consideradas

### 1. Manter a `PharmacyApplicationService` como estava

**Não escolhida.**

Manter todas as responsabilidades na mesma classe continuaria aumentando sua complexidade e dificultaria futuras alterações.

### 2. Refatorar também as classes do pacote `patterns`

**Não escolhida.**

O pacote `patterns` foi preservado porque seu conteúdo não faz parte do escopo de alteração desta etapa da disciplina.

### 3. Separar responsabilidades em serviços específicos

**Escolhida.**

Permite reduzir as responsabilidades da `PharmacyApplicationService` sem realizar uma mudança completa na arquitetura existente.

## Consequências

### Positivas

- A `PharmacyApplicationService` passa a concentrar menos responsabilidades;
- cada serviço possui uma função mais clara;
- o código fica mais organizado e compreensível;
- alterações futuras podem ser realizadas de forma mais localizada;
- melhora a manutenibilidade do sistema;
- facilita a identificação de responsabilidades;
- mantém o pacote `patterns` preservado.

### Negativas

- o projeto passa a possuir uma quantidade maior de classes;
- a `PharmacyApplicationService` ainda depende de diferentes serviços para coordenar o fluxo;
- outras responsabilidades do sistema poderão precisar de novas refatorações nas próximas aulas.

### Neutras / Trabalhos futuros

Esta decisão não representa uma refatoração completa do PharmaLink.

Novos problemas de design poderão ser identificados durante as próximas aulas e, caso resultem em decisões arquiteturais significativas, deverão ser registrados em novas ADRs.

## Resultado

A estrutura passou de uma classe com diversas responsabilidades para uma organização na qual a `PharmacyApplicationService` coordena serviços especializados.

### Antes

`PharmacyApplicationService`

- coordenação do pedido;
- validação de receita;
- estoque;
- pagamento;
- entrega;
- notificações.

### Depois

`PharmacyApplicationService`

- coordena o fluxo da aplicação;
- delega validação de receita para `PrescriptionService`;
- delega operações de estoque para `StockService`;
- delega pagamento para `PaymentService`;
- delega entrega para `DeliveryService`;
- delega notificações para `NotificationService`.

## Relação com outras decisões

Esta ADR complementa a `ADR-0001 — Arquitetura inicial`.

A arquitetura inicial do PharmaLink continua sendo preservada, enquanto esta decisão registra especificamente a melhoria na distribuição das responsabilidades da camada de aplicação.