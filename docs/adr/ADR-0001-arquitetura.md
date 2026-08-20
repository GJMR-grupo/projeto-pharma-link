# ADR-0001 — Arquitetura Inicial

## Status

Aceito.

## Contexto

O PharmaLink foi recebido como um sistema legado que já possui diferentes classes e responsabilidades relacionadas a medicamentos, estoque, pedidos, pagamento, entrega e notificações.

Durante a análise inicial do projeto, identificamos que já existia uma proposta de utilização de arquitetura em camadas. Porém, o registro da decisão estava incompleto e não explicava de forma clara o motivo da escolha, as alternativas consideradas e as consequências dessa decisão.

Como a Aula 01 tem como objetivo principal compreender e organizar o sistema antes de realizar alterações técnicas, a equipe decidiu analisar e documentar melhor a arquitetura existente, sem realizar uma refatoração antecipada do projeto.

## Decisão

Manter inicialmente o PharmaLink organizado utilizando uma arquitetura em camadas.

A organização atual do sistema permite identificar responsabilidades relacionadas a:

- **Domínio:** medicamentos, pedidos, prescrições e lotes de estoque;
- **Aplicação:** coordenação dos casos de uso e do fluxo principal do sistema;
- **Persistência:** armazenamento e consulta das informações por meio dos repositórios;
- **Integrações externas:** comunicação com serviços de pagamento, entrega, fornecedor e notificações.

Nesta etapa, a equipe decidiu preservar a estrutura atual enquanto realiza a análise gradual do sistema durante as próximas aulas.

## Justificativa

A arquitetura em camadas foi mantida inicialmente porque:

- facilita a compreensão da estrutura do projeto;
- permite separar diferentes tipos de responsabilidades;
- facilita a manutenção e evolução gradual do sistema;
- permite identificar com maior clareza problemas de design;
- evita alterações estruturais antes da análise completa do sistema legado.

A possibilidade de utilização de microsserviços não será considerada uma necessidade atual do projeto.

Caso futuramente seja identificada a necessidade de mudança arquitetural, uma nova decisão deverá ser analisada e documentada.

## Consequências

### Consequências positivas

- Melhor organização das responsabilidades do sistema;
- maior facilidade para compreender a estrutura do projeto;
- possibilidade de evolução gradual;
- facilidade para localizar diferentes partes da aplicação;
- evita refatorações prematuras durante a análise inicial.

### Consequências negativas

- A utilização de camadas não garante que todas as classes estejam corretamente organizadas;
- algumas classes podem possuir responsabilidades excessivas;
- podem existir dependências inadequadas entre componentes;
- a arquitetura poderá precisar de ajustes conforme novos problemas forem identificados durante as próximas aulas.

## Alternativas Consideradas

### 1. Manter o projeto sem documentar a arquitetura

**Não escolhida.**

Essa alternativa dificultaria o entendimento do sistema e manteria a decisão arquitetural sem uma justificativa adequada.

### 2. Refatorar imediatamente toda a arquitetura

**Não escolhida.**

A Aula 01 possui como objetivo compreender e organizar o sistema antes de realizar alterações técnicas significativas.

### 3. Migrar diretamente para microsserviços

**Não escolhida.**

Neste momento, não existem informações suficientes que justifiquem o aumento de complexidade causado por uma arquitetura de microsserviços.

### 4. Manter inicialmente a arquitetura em camadas

**Escolhida.**

Essa alternativa permite compreender o sistema atual e realizar futuras mudanças de forma gradual e justificada.

## Observações

Esta ADR registra a decisão arquitetural inicial do PharmaLink e complementa o registro legado que estava incompleto.

A decisão poderá ser revisada futuramente caso a equipe identifique novos problemas, necessidades ou alternativas durante a evolução do projeto.