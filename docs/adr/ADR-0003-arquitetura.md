# ADR-003 — Organização da Arquitetura em Componentes

- **Status:** Aceita
- **Data:** 27/08/2026
- **Decisores:** Grupo PharmaLink
- **Contexto:** Projeto PharmaLink

## Contexto

O PharmaLink precisa organizar suas responsabilidades de forma que as
funcionalidades de negócio, persistência de dados e integrações com sistemas
legados não fiquem diretamente acopladas.

O projeto possui componentes como:

- `PharmaLinkFacade`
- `PharmacyApplicationService`
- `OrderFactory`
- `OrderPublisher`
- `PaymentAdapter`
- `SupplierAdapter`
- `InMemoryMedicineRepository`
- `InMemoryStockRepository`

Além disso, existem entidades de domínio como `Medicine`, `Order`,
`Prescription` e `StockLot`, e integrações com sistemas legados como
`PaymentLegacyGateway` e `SupplierLegacyApi`.

## Decisão

Adotamos uma arquitetura organizada em componentes com responsabilidades
separadas e utilizamos padrões de projeto para controlar a comunicação entre
eles.

A organização principal será:

### 1. Domínio

Responsável por representar os conceitos do negócio:

- `Medicine`
- `Order`
- `Prescription`
- `StockLot`

### 2. Serviço de aplicação

O `PharmacyApplicationService` será responsável por coordenar os fluxos
principais da aplicação.

### 3. Facade

A `PharmaLinkFacade` será utilizada como uma interface simplificada de acesso
às funcionalidades da aplicação.

### 4. Persistência

Os componentes:

- `InMemoryMedicineRepository`
- `InMemoryStockRepository`

serão responsáveis pelo armazenamento e recuperação dos dados.

### 5. Integrações

Serão utilizados:

- `PaymentAdapter`
- `SupplierAdapter`

para evitar que o restante da aplicação dependa diretamente das interfaces
dos sistemas legados.

### 6. Comunicação por eventos

O padrão Observer será utilizado por meio de:

- `OrderPublisher`
- `OrderObserver`
- `AuditObserver`
- `CustomerNotificationObserver`

permitindo que diferentes componentes reajam aos eventos de pedidos.

### 7. Estratégias e criação de objetos

O padrão Strategy será utilizado através de:

- `DiscountStrategy`
- `DiscountCalculator`

e o padrão Factory será utilizado através de:

- `OrderFactory`

## Justificativa

A decisão foi tomada para reduzir o acoplamento entre as partes do sistema e
separar responsabilidades.

O uso de Adapter permite modificar ou substituir integrações legadas sem
alterar diretamente a lógica principal da aplicação.

O Facade simplifica o acesso às funcionalidades do sistema. O Observer
permite adicionar novos comportamentos relacionados aos eventos de pedidos
sem modificar diretamente o componente que gera o evento.

O Strategy permite alterar regras de desconto de maneira flexível, enquanto
o Factory centraliza a criação de pedidos.

Essa organização facilita manutenção, testes, reutilização e evolução do
sistema.

## Consequências positivas

- Menor acoplamento entre componentes.
- Maior separação de responsabilidades.
- Facilita a substituição de sistemas legados.
- Facilita a inclusão de novos observadores.
- Facilita a alteração das regras de negócio.
- Melhora a manutenção e compreensão do código.
- Permite representar a arquitetura através de um diagrama de componentes.

## Consequências negativas

- A arquitetura possui mais classes e abstrações.
- O fluxo de execução pode ficar menos óbvio para quem está conhecendo o
  projeto.
- Os padrões adicionam uma pequena complexidade inicial.

## Evidências no código

A decisão pode ser rastreada para os seguintes componentes:

- `src/main/java/br/edu/pharmalink/patterns/facade/PharmaLinkFacade.java`
- `src/main/java/br/edu/pharmalink/service/PharmacyApplicationService.java`
- `src/main/java/br/edu/pharmalink/patterns/adapter/PaymentAdapter.java`
- `src/main/java/br/edu/pharmalink/patterns/adapter/SupplierAdapter.java`
- `src/main/java/br/edu/pharmalink/patterns/observer/OrderPublisher.java`
- `src/main/java/br/edu/pharmalink/patterns/observer/OrderObserver.java`
- `src/main/java/br/edu/pharmalink/patterns/observer/AuditObserver.java`
- `src/main/java/br/edu/pharmalink/patterns/observer/CustomerNotificationObserver.java`
- `src/main/java/br/edu/pharmalink/patterns/strategy/DiscountStrategy.java`
- `src/main/java/br/edu/pharmalink/patterns/strategy/DiscountCalculator.java`
- `src/main/java/br/edu/pharmalink/patterns/factory/OrderFactory.java`

## Relação com o diagrama de componentes

O diagrama representa visualmente essa decisão arquitetural.

As cores representam categorias:

- 🟢 Verde — componentes da aplicação e padrões arquiteturais.
- 🔵 Azul — domínio/modelo.
- 🟣 Roxo — persistência/repositórios.
- 🔴 Vermelho — sistemas externos/legados.
- 🟡 Amarelo — interfaces/contratos.

As setas representam os conectores e as formas de comunicação entre os
componentes.