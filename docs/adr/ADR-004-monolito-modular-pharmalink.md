# ADR-004 — Evolução da arquitetura do PharmaLink para Monólito Modular

## Status

**Aceito**

## Data

09/09/2026

## Participantes

- Michelly Lima
- Ryan Rodrigues
- Júllya Andrade
- Guilherme Felipe

---

## Contexto

Na análise arquitetural realizada na Aula 06, foi identificado que o PharmaLink está organizado atualmente como um **monólito em camadas simples**.

A estrutura atual possui separação em pacotes como `model`, `repository`, `service`, `legacy` e `patterns`, porém ainda existem responsabilidades concentradas e dependências diretas entre a camada de aplicação e integrações externas.

O principal ponto de atenção é o `PharmacyApplicationService`, que participa de diferentes etapas do fluxo do sistema, envolvendo pedido, estoque, receita, pagamento, entrega, notificação e eventos.

Também foram identificados riscos relacionados a requisitos não funcionais importantes para o PharmaLink:

- **Manutenibilidade:** responsabilidades concentradas aumentam o acoplamento e dificultam alterações e testes.
- **Segurança:** o fluxo de medicamentos que exigem receita precisa possuir validações mais claras, assim como o tratamento das operações de pagamento.
- **Disponibilidade e confiabilidade:** falhas em notificações ou auditoria não devem comprometer a operação principal.
- **Desempenho:** integrações externas não devem bloquear desnecessariamente o fluxo principal.

Diante disso, foi necessário avaliar se a arquitetura atual deveria ser mantida ou evoluída.

---

## Alternativas consideradas

### Alternativa A — Manter o monólito em camadas simples

Manter a estrutura atual, realizando apenas pequenas correções internas.

**Vantagens:**
- menor complexidade operacional;
- baixo custo de mudança;
- continuidade da estrutura já existente.

**Desvantagens:**
- mantém responsabilidades concentradas;
- mantém maior acoplamento com integrações;
- não resolve adequadamente os riscos identificados na análise.

**Pontuação na matriz:** 45 pontos.

---

### Alternativa B — Monólito modular em camadas com Ports/Adapters e eventos internos

Manter o PharmaLink como uma única aplicação, mas evoluir sua organização interna para módulos e serviços com responsabilidades mais específicas.

As integrações externas passam a ser acessadas por interfaces (ports), enquanto as implementações concretas funcionam como adapters.

Eventos internos podem ser utilizados para separar atividades secundárias, como notificação e auditoria, da operação principal.

**Vantagens:**
- reduz o acoplamento;
- melhora a separação de responsabilidades;
- facilita testes;
- melhora a manutenção;
- permite tratar falhas de integrações de maneira mais controlada;
- mantém baixa complexidade operacional por continuar sendo uma única aplicação.

**Desvantagens:**
- exige refatoração;
- aumenta a quantidade de interfaces e componentes;
- exige revisão dos testes e dos fluxos existentes.

**Pontuação na matriz:** 69 pontos.

---

### Alternativa C — Microsserviços / SOA

Separar responsabilidades como pedidos, estoque, pagamento, entrega e notificação em serviços independentes.

**Vantagens:**
- maior isolamento entre responsabilidades;
- possibilidade de implantação e escala independentes;
- maior isolamento de determinadas falhas.

**Desvantagens:**
- aumenta significativamente a complexidade;
- exige comunicação entre serviços;
- exige contratos, observabilidade e tratamento de falhas distribuídas;
- não é proporcional ao estágio atual do PharmaLink.

**Pontuação na matriz:** 60 pontos.

---

## Decisão

Foi escolhida a **Alternativa B — Monólito Modular em Camadas com Ports/Adapters e eventos internos**.

O PharmaLink continuará sendo uma única aplicação, evitando a complexidade operacional de microsserviços neste momento.

Entretanto, sua estrutura interna deverá evoluir para possuir fronteiras mais claras entre responsabilidades.

A evolução proposta considera:

- separar responsabilidades de pedido, estoque, receita, pagamento, entrega e notificação;
- criar interfaces para integrações externas;
- utilizar adapters para implementações relacionadas às APIs legadas;
- permitir que notificação e auditoria sejam tratadas de forma independente;
- reforçar as validações relacionadas a medicamentos que exigem receita;
- impedir que o fluxo de entrega prossiga quando o pagamento não for confirmado.

Uma organização possível é:

```text
Entrada
  |
  v
Camada de Aplicação
  |
  +-- OrderService
  +-- StockService
  +-- PrescriptionService
  +-- PaymentService
  +-- DeliveryService
  +-- NotificationService
  |
  v
Domínio
  |
  +--> Repositórios
  |
  +--> Ports / Interfaces
          |
          v
       Adapters
          |
          +-- PaymentLegacyGateway
          +-- DeliveryPartnerApi
          +-- SupplierLegacyApi
          +-- WhatsappLegacyApi
```

---

## Justificativa

A decisão foi baseada nos requisitos de qualidade analisados e na matriz de decisão arquitetural.

A Alternativa B obteve **69 pontos**, enquanto a arquitetura atual obteve **45 pontos** e a alternativa de microsserviços/SOA obteve **60 pontos**.

Apesar de microsserviços apresentarem benefícios de isolamento e escalabilidade, essa alternativa adicionaria complexidade de comunicação, implantação, monitoramento e tratamento de falhas que o projeto ainda não necessita.

O monólito modular permite melhorar a arquitetura de forma incremental, utilizando a estrutura já existente no PharmaLink e evitando a criação de tecnologia apenas para aumentar a complexidade.

---

## Trade-off

O principal trade-off aceito pelo grupo é:

> **Aumentar a organização e a quantidade de componentes internos para reduzir acoplamento e melhorar manutenção, segurança e confiabilidade, sem assumir a complexidade operacional de uma arquitetura distribuída.**

### Ganhos

- melhor separação de responsabilidades;
- menor dependência direta de APIs legadas;
- maior testabilidade;
- manutenção mais simples;
- melhor tratamento de falhas;
- arquitetura preparada para evoluções futuras.

### Custos e consequências

- necessidade de refatoração;
- criação de novas interfaces e serviços;
- revisão de dependências;
- atualização e criação de testes;
- maior disciplina para preservar as fronteiras entre os módulos.

---

## Consequências da decisão

### Consequências positivas

1. `PharmacyApplicationService` deixa de ser o principal ponto de concentração de responsabilidades.
2. Integrações externas ficam isoladas por contratos.
3. Alterações em APIs legadas passam a ter menor impacto sobre as regras de negócio.
4. Notificação e auditoria podem evoluir sem bloquear diretamente a operação principal.
5. O sistema continua simples de executar e implantar.
6. A arquitetura passa a refletir melhor os requisitos não funcionais identificados na Aula 06.

### Consequências negativas

1. O número de classes e interfaces tende a aumentar.
2. Será necessário refatorar parte do código existente.
3. A equipe precisará manter as dependências entre camadas e módulos sob controle.
4. A evolução deverá ser acompanhada por testes para evitar regressões.

---

## Relação com decisões anteriores

Esta ADR **evolui a decisão registrada na ADR-0001**, que definiu inicialmente uma arquitetura em camadas.

A arquitetura em camadas não será descartada. Ela será mantida como base, porém com módulos mais bem definidos, contratos para integrações e melhor separação de responsabilidades.

Portanto, esta decisão não representa uma migração para microsserviços, mas uma evolução controlada da arquitetura existente.

---

## Evidências utilizadas

- `Main.java`;
- `PharmacyApplicationService.java`;
- pacote `model`;
- pacote `repository`;
- pacote `legacy`;
- pacote `patterns`;
- `PaymentLegacyGateway.java`;
- `DeliveryPartnerApi.java`;
- `SupplierLegacyApi.java`;
- `WhatsappLegacyApi.java`;
- `OrderPublisher`;
- requisitos funcionais e não funcionais analisados na Aula 06;
- matriz de decisão arquitetural da Aula 06.

---

## Resultado esperado

Após a evolução, o PharmaLink deverá continuar simples para execução e implantação, porém com responsabilidades mais bem distribuídas e integrações externas desacopladas das regras centrais do sistema.

A decisão também deixa uma base mais adequada para futuras evoluções arquiteturais caso novos requisitos de escala, disponibilidade ou integração realmente justifiquem mudanças maiores.
