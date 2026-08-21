# Evidência da Entrega

## Aula

Aula 02 — Refatoração e Separação de Responsabilidades

## Problema observado

Durante a análise do código, foi identificado que a classe `PharmacyApplicationService` concentrava muitas responsabilidades.

Além de coordenar o fluxo da aplicação, a classe também possuía responsabilidades relacionadas à validação de receita, controle de estoque, pagamento, entrega e notificações.

Essa concentração dificultava a organização, compreensão e manutenção do código.

## Alteração realizada

Foi realizada uma refatoração da classe `PharmacyApplicationService`, separando algumas de suas responsabilidades em serviços específicos.

Foram criadas as seguintes classes:

- `PrescriptionService` — responsável pela validação relacionada à receita médica;
- `StockService` — responsável pelas operações relacionadas ao estoque e aos lotes;
- `PaymentService` — responsável pelo processamento do pagamento;
- `DeliveryService` — responsável pelas operações relacionadas à entrega;
- `NotificationService` — responsável pelo envio de notificações.

Após a refatoração, a `PharmacyApplicationService` passou a atuar principalmente na coordenação do fluxo da aplicação, delegando operações específicas para os respectivos serviços.

O pacote `patterns` foi preservado e não recebeu alterações.

A decisão de separar essas responsabilidades foi registrada na `ADR-0002`.

## Critério de aceitação

A entrega foi considerada concluída quando:

- a `PharmacyApplicationService` deixou de concentrar diretamente todas as responsabilidades identificadas;
- as responsabilidades de receita, estoque, pagamento, entrega e notificação foram separadas em classes específicas;
- o fluxo principal continuou sendo coordenado pela `PharmacyApplicationService`;
- o projeto continuou compilando e executando após a refatoração;
- nenhuma classe do pacote `patterns` foi modificada;
- a decisão da refatoração foi documentada na `ADR-0002`.

## Evidências

- **Issue:** Aula 02 — Refatoração e Separação de Responsabilidades
- **Branch:** `aula-02`
- **Commits:**
  - `refactor: separa responsabilidades do PharmacyApplicationService`
  - `docs: registra ADR-0002 sobre separacao de responsabilidades`
- **PR:** adicionar após a criação do Pull Request
- **Arquivos/diagramas:**
  - `src/main/java/br/edu/pharmalink/service/PharmacyApplicationService.java`
  - `src/main/java/br/edu/pharmalink/service/PrescriptionService.java`
  - `src/main/java/br/edu/pharmalink/service/StockService.java`
  - `src/main/java/br/edu/pharmalink/service/PaymentService.java`
  - `src/main/java/br/edu/pharmalink/service/DeliveryService.java`
  - `src/main/java/br/edu/pharmalink/service/NotificationService.java`
  - `docs/adr/ADR-0002-separacao-responsabilidades.md`
  - `evidencias/aula-02.md`

- **Participantes e contribuições:**
  - **Júllya:** análise e apoio na identificação das responsabilidades do sistema;
  - **Guilherme Felipe:** análise do fluxo e das regras envolvidas;
  - **Michelly Lima:** análise e refatoração das classes e separação das responsabilidades;
  - **Ryan Rodrigues:** análise da organização do sistema e apoio na documentação;
  - **Equipe:** revisão e validação das alterações realizadas.