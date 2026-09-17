# ADR-08 --- Consulta de Disponibilidade de Medicamentos em Estoque

## Status

Aceito.

## Data

17/09/2026

## Participantes

-   Júllya Andrade
-   Guilherme Felipe
-   Michelly Lima
-   Ryan Rodrigues

## Contexto

Durante a Aula 08, a equipe trabalhou a relação entre necessidade do
usuário, fluxo, interface, modelo, arquitetura e código no projeto
PharmaLink.

Para representar essa ligação de ponta a ponta, foi escolhida uma
funcionalidade pequena relacionada ao domínio do sistema: a consulta de
disponibilidade de medicamentos em estoque.

O usuário precisa conseguir consultar um medicamento e identificar se
ele está disponível antes de prosseguir com outras operações no sistema.

A funcionalidade envolve principalmente responsabilidades relacionadas
a:

-   identificação do medicamento;
-   consulta das informações do medicamento;
-   verificação da quantidade disponível;
-   retorno da situação do estoque;
-   apresentação de uma mensagem quando o medicamento estiver
    indisponível ou não for encontrado.

A solução também precisa permanecer coerente com a separação de
responsabilidades definida anteriormente no projeto, principalmente com
a existência do `StockService`, criado para concentrar operações
relacionadas ao estoque.

O requisito definido para esta funcionalidade é:

**RF-08-01 --- O usuário deve poder consultar a disponibilidade de um
medicamento e visualizar sua quantidade em estoque quando disponível.**

## Decisão

A equipe decidiu implementar e representar a consulta de disponibilidade
de medicamentos utilizando as responsabilidades já separadas na
arquitetura do PharmaLink.

A funcionalidade seguirá o seguinte fluxo:

1.  o usuário informa o medicamento que deseja consultar;
2.  a interface recebe a solicitação;
3.  o sistema verifica se o medicamento existe;
4.  o `StockService` realiza a consulta relacionada ao estoque;
5.  os dados necessários são recuperados pelos componentes responsáveis
    pela persistência;
6.  o sistema verifica a quantidade disponível;
7.  a interface apresenta o resultado ao usuário.

### `Interface / Controller`

Responsável por receber a solicitação de consulta do usuário,
encaminhá-la para o serviço responsável e apresentar o resultado.

### `StockService`

Responsável pelas regras relacionadas à consulta de estoque, incluindo a
verificação da disponibilidade e da quantidade do medicamento.

### `Repository`

Responsável pelo acesso aos dados necessários para localizar o
medicamento e consultar suas informações de estoque.

### `Medicamento`

Representa as informações do medicamento utilizadas durante a consulta.

### `Estoque`

Representa as informações relacionadas à quantidade e disponibilidade do
medicamento.

O fluxo deve tratar três resultados principais:

-   medicamento encontrado e disponível;
-   medicamento encontrado, porém sem quantidade disponível;
-   medicamento não encontrado.

A decisão mantém a organização em camadas existente no projeto e utiliza
o `StockService` como responsável pelas operações relacionadas ao
estoque.

## Justificativa

A decisão foi tomada para permitir que uma necessidade real do usuário
seja representada de forma coerente desde o requisito até a
implementação.

A consulta de disponibilidade é uma funcionalidade pequena o suficiente
para ser demonstrada durante a atividade, mas também envolve diferentes
partes do software.

Ao utilizar o `StockService`, a equipe mantém a separação de
responsabilidades definida anteriormente e evita colocar regras
relacionadas ao estoque diretamente na interface ou na classe que
coordena o fluxo geral da aplicação.

Além disso, a funcionalidade pode ser representada por diagramas
UML/Mermaid, permitindo visualizar os atores, classes, mensagens e
etapas do processo.

## Alternativas Consideradas

### 1. Realizar a consulta diretamente na interface

**Não escolhida.**

A interface ficaria responsável por regras que pertencem ao domínio e ao
serviço de estoque, aumentando a concentração de responsabilidades e
dificultando futuras alterações.

### 2. Colocar a consulta diretamente na `PharmacyApplicationService`

**Não escolhida.**

Essa alternativa voltaria a concentrar operações específicas na classe
que deve atuar principalmente como coordenadora do fluxo da aplicação.

Isso também iria contra a separação de responsabilidades registrada na
ADR-0002.

### 3. Utilizar o `StockService` para realizar a consulta de disponibilidade

**Escolhida.**

Essa opção mantém a responsabilidade relacionada ao estoque no serviço
específico já existente no projeto e preserva a organização definida nas
decisões anteriores.

## Consequências

### Positivas

-   mantém as responsabilidades de estoque concentradas no
    `StockService`;
-   preserva a separação de responsabilidades realizada anteriormente;
-   facilita a compreensão do fluxo da funcionalidade;
-   permite relacionar requisito, interface, modelo, arquitetura e
    código;
-   facilita a criação de testes para diferentes situações de estoque;
-   mantém a interface responsável principalmente pela interação com o
    usuário;
-   facilita futuras alterações nas regras relacionadas à
    disponibilidade.

### Negativas

-   a funcionalidade envolve comunicação entre diferentes partes do
    sistema;
-   é necessário manter coerência entre os modelos, serviços e
    componentes de persistência;
-   novos tratamentos poderão ser necessários caso as regras de estoque
    sejam ampliadas.

### Neutras / Trabalhos futuros

A consulta desenvolvida nesta etapa representa apenas uma parte do fluxo
relacionado aos medicamentos.

Em próximas evoluções, poderão ser adicionadas novas informações à
consulta ou novas regras relacionadas a estoque, reserva e
disponibilidade.

Caso essas alterações representem decisões arquiteturais significativas,
deverão ser registradas em novas ADRs.

## Resultado

A funcionalidade passa a possuir um fluxo definido e rastreável desde a
necessidade do usuário até as responsabilidades do código.

### Fluxo definido

`Usuário`

-   informa o medicamento que deseja consultar.

`Interface / Controller`

-   recebe a solicitação;
-   encaminha a consulta ao serviço;
-   apresenta o resultado ao usuário.

`StockService`

-   verifica as informações relacionadas ao estoque;
-   determina se existe quantidade disponível;
-   retorna o resultado da consulta.

`Repository`

-   recupera os dados necessários para a consulta.

`Medicamento / Estoque`

-   representam os dados utilizados durante o processo.

### Resultados possíveis

**Medicamento disponível**

O sistema informa que o medicamento está disponível e apresenta sua
quantidade em estoque.

**Medicamento indisponível**

O sistema informa que o medicamento foi encontrado, porém não existe
quantidade disponível.

**Medicamento não encontrado**

O sistema informa ao usuário que o medicamento consultado não foi
localizado.

## Diagramas relacionados

A funcionalidade foi documentada utilizando os seguintes diagramas:

-   `diagrama-casos-de-uso.md` --- apresenta os atores e os objetivos
    relacionados à consulta;
-   `diagrama-classes.md` --- apresenta os conceitos, atributos,
    operações e relações envolvidos;
-   `diagrama-sequencia.md` --- apresenta as mensagens trocadas durante
    a execução da consulta;
-   `diagrama-atividades.md` --- apresenta as etapas e decisões
    existentes no processo.

## Rastreabilidade

  -------------------------------------------------------------------------------
  Requisito         Interface      Modelo         Arquitetura    Código
  ----------------- -------------- -------------- -------------- ----------------
  RF-08-01 ---      Interface de   Medicamento e  Interface →    `StockService` e
  Consultar         consulta de    Estoque        Service →      componentes
  disponibilidade   medicamento                   Repository     relacionados
  de medicamento                                                 

  -------------------------------------------------------------------------------

A rastreabilidade permite identificar como a necessidade escolhida
aparece nos diferentes artefatos produzidos durante a atividade.

## Relação com outras decisões

Esta ADR complementa a `ADR-0001 — Arquitetura inicial` e a
`ADR-0002 — Separação de Responsabilidades do PharmacyApplicationService`.

A arquitetura inicial do PharmaLink continua sendo preservada.

A separação de responsabilidades definida na ADR-0002 também permanece
válida, principalmente em relação ao `StockService`, que continua
responsável pelas operações relacionadas ao estoque.

A ADR-08 registra especificamente como essa organização é aplicada à
funcionalidade de consulta de disponibilidade trabalhada durante a Aula
08.
