# ENTREGA 03 — Componentes, Conectores e Interfaces

## Projeto: PharmaLink

**Disciplina:** Design de Software  
**Equipe:** [Nomes dos integrantes]  
**Professor:** [Nome do professor]  
**Data:** [Data da entrega]

---

# 1. Objetivo

Esta entrega tem como objetivo documentar a arquitetura de componentes
do sistema PharmaLink, identificando:

- os principais componentes;
- suas responsabilidades;
- as interfaces utilizadas;
- os conectores entre os componentes;
- as evidências dessas relações no código-fonte;
- as decisões arquiteturais adotadas.

A documentação busca garantir rastreabilidade entre a arquitetura
representada nos diagramas e sua implementação no código.

---

# 2. Visão Geral da Arquitetura

O PharmaLink foi organizado em componentes com responsabilidades
separadas.

A arquitetura possui componentes relacionados a:

- domínio;
- serviços de aplicação;
- persistência;
- integração com sistemas legados;
- criação de objetos;
- publicação de eventos;
- estratégias de negócio.

Foram utilizados padrões de projeto como:

- Facade;
- Adapter;
- Observer;
- Strategy;
- Factory;
- Abstract Factory.

---

# 3. Diagrama de Componentes

> Inserir aqui o diagrama criado no Draw.io.

**Arquivo do diagrama:**

`docs/diagramas/PharmaLink_UML.drawio`

**Imagem exportada:**

`docs/diagramas/componentes.png`

![Diagrama de Componentes](../diagramas/componentes.png)

## 3.1 Legenda

| Representação | Significado |
|---|---|
| 🟢 Verde | Componentes da aplicação / padrões |
| 🔵 Azul | Domínio / modelo |
| 🟣 Roxo | Persistência / repositórios |
| 🔴 Vermelho | Sistemas externos / legados |
| 🟡 Amarelo | Interfaces / contratos |
| → | Dependência / comunicação |
| - -▷ | Implementação de interface |

---

# 4. Componentes e Responsabilidades

## 4.1 PharmaLinkFacade

**Tipo:** Facade

**Responsabilidade:**  
Fornecer uma interface simplificada para acesso às funcionalidades
principais do PharmaLink.

**Evidência no código:**

`src/main/java/br/edu/pharmalink/patterns/facade/PharmaLinkFacade.java`

---

## 4.2 PharmacyApplicationService

**Tipo:** Application Service

**Responsabilidade:**  
Coordenar os fluxos de aplicação e a comunicação entre os diferentes
componentes.

**Evidência no código:**

`src/main/java/br/edu/pharmalink/service/PharmacyApplicationService.java`

---

## 4.3 Componentes de Domínio

### Medicine

Representa um medicamento.

**Evidência:**

`src/main/java/br/edu/pharmalink/model/Medicine.java`

### Order

Representa um pedido realizado no sistema.

**Evidência:**

`src/main/java/br/edu/pharmalink/model/Order.java`

### Prescription

Representa uma receita associada às regras do domínio.

**Evidência:**

`src/main/java/br/edu/pharmalink/model/Prescription.java`

### StockLot

Representa um lote de medicamentos disponível no estoque.

**Evidência:**

`src/main/java/br/edu/pharmalink/model/StockLot.java`

---

## 4.4 Persistência

### InMemoryMedicineRepository

**Responsabilidade:**  
Armazenar e recuperar medicamentos em memória.

**Evidência:**

`src/main/java/br/edu/pharmalink/repository/InMemoryMedicineRepository.java`

### InMemoryStockRepository

**Responsabilidade:**  
Armazenar e recuperar informações relacionadas ao estoque.

**Evidência:**

`src/main/java/br/edu/pharmalink/repository/InMemoryStockRepository.java`

---

## 4.5 Integrações

### PaymentAdapter

**Padrão:** Adapter

**Responsabilidade:**  
Adaptar a comunicação entre o PharmaLink e o sistema legado de
pagamentos.

**Evidência:**

`src/main/java/br/edu/pharmalink/patterns/adapter/PaymentAdapter.java`

### SupplierAdapter

**Padrão:** Adapter

**Responsabilidade:**  
Adaptar a comunicação entre o PharmaLink e a API legada de fornecedores.

**Evidência:**

`src/main/java/br/edu/pharmalink/patterns/adapter/SupplierAdapter.java`

---

# 5. Interfaces

## 5.1 OrderObserver

**Tipo:** Interface

**Responsabilidade:**  
Definir o contrato utilizado pelos componentes interessados em receber
eventos relacionados aos pedidos.

**Implementações:**

- `AuditObserver`
- `CustomerNotificationObserver`

**Evidência:**

`src/main/java/br/edu/pharmalink/patterns/observer/OrderObserver.java`

---

## 5.2 DiscountStrategy

**Tipo:** Interface

**Responsabilidade:**  
Definir o contrato para estratégias de cálculo de desconto.

**Utilizada por:**

`DiscountCalculator`

**Evidência:**

`src/main/java/br/edu/pharmalink/patterns/strategy/DiscountStrategy.java`

---

# 6. Conectores e Evidências no Código

Os conectores representam as relações e formas de comunicação entre
os componentes da arquitetura.

| Origem | Conector | Destino | Responsabilidade | Evidência |
|---|---|---|---|---|
| PharmaLinkFacade | chamada | PharmacyApplicationService | Encaminhar operações | `PharmaLinkFacade.java` |
| PharmacyApplicationService | acesso | InMemoryMedicineRepository | Acessar medicamentos | `PharmacyApplicationService.java` |
| PharmacyApplicationService | acesso | InMemoryStockRepository | Acessar estoque | `PharmacyApplicationService.java` |
| PharmacyApplicationService | chamada | PaymentAdapter | Processar integração de pagamento | `PharmacyApplicationService.java` |
| PharmacyApplicationService | chamada | SupplierAdapter | Integrar fornecedores | `PharmacyApplicationService.java` |
| PaymentAdapter | adaptação | PaymentLegacyGateway | Adaptar sistema legado | `PaymentAdapter.java` |
| SupplierAdapter | adaptação | SupplierLegacyApi | Adaptar API legada | `SupplierAdapter.java` |
| OrderPublisher | notificação | OrderObserver | Publicar eventos | `OrderPublisher.java` |
| AuditObserver | implementação | OrderObserver | Observar eventos para auditoria | `AuditObserver.java` |
| CustomerNotificationObserver | implementação | OrderObserver | Observar eventos para notificação | `CustomerNotificationObserver.java` |
| OrderFactory | criação | Order | Criar pedidos | `OrderFactory.java` |
| DiscountCalculator | estratégia | DiscountStrategy | Utilizar estratégia de desconto | `DiscountCalculator.java` |

---

# 7. Evidências Detalhadas

> Nesta seção devem ser colocados pequenos trechos reais do código
> comprovando os principais conectores.

## 7.1 Adapter de Pagamento

**Arquivo:** `PaymentAdapter.java`

```java
// Inserir aqui o trecho real do código que utiliza
// PaymentLegacyGateway.