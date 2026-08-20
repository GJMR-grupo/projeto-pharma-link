# Arquitetura Inicial — PharmaLink

## Visão Geral

Durante a análise inicial do PharmaLink, a equipe identificou que o projeto possui uma organização baseada em diferentes responsabilidades, envolvendo domínio, aplicação, persistência e integrações externas.

O projeto legado apresenta uma proposta inicial de arquitetura em camadas.

Nesta primeira aula, a equipe optou por compreender e documentar a estrutura existente sem realizar alterações arquiteturais significativas.

## Organização identificada

De forma simplificada, o sistema pode ser compreendido nas seguintes partes:

### Domínio

Representa os principais elementos relacionados ao negócio da farmácia.

Exemplos:

- medicamentos;
- pedidos;
- prescrições;
- lotes de estoque.

### Aplicação

Responsável por coordenar o funcionamento das principais operações do sistema.

A classe `PharmacyApplicationService` participa da coordenação de operações relacionadas aos pedidos, estoque, pagamento, entrega e notificações.

### Persistência

Responsável pelo armazenamento e consulta das informações utilizadas pelo sistema.

O projeto possui repositórios para acesso aos dados de medicamentos e estoque.

### Integrações externas

O PharmaLink possui comunicação com serviços externos relacionados a:

- pagamento;
- entrega;
- fornecedores;
- notificações.

## Fluxo simplificado

A estrutura inicial pode ser representada da seguinte maneira:

Aplicação  
↓  
Domínio  
↓  
Persistência / Integrações externas

## Decisão inicial

Nesta etapa, a equipe decidiu manter a arquitetura existente enquanto realiza a análise gradual do sistema.

Não foram realizadas mudanças estruturais significativas, pois o objetivo da Aula 01 é compreender e organizar o projeto antes de realizar refatorações.

A decisão arquitetural inicial está registrada com mais detalhes em:

`docs/adr/ADR-0001-arquitetura.md`

## Considerações

A arquitetura atual poderá ser revisada durante as próximas aulas caso sejam identificados problemas de acoplamento, responsabilidades, organização ou integração.

Nesta primeira etapa, nenhuma mudança arquitetural significativa foi implementada.