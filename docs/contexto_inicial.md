# Contexto Inicial — PharmaLink

## Visão Geral

O PharmaLink é um sistema voltado para o funcionamento de uma farmácia digital.

O projeto envolve o gerenciamento de medicamentos, estoque, pedidos, pagamentos, entregas e notificações ao cliente.

Nesta primeira etapa, o objetivo da equipe foi compreender o funcionamento geral do sistema e identificar os principais elementos presentes no projeto legado.

## Principais envolvidos

Durante a análise inicial, foram identificados alguns participantes relacionados ao funcionamento do PharmaLink:

- Cliente;
- Farmacêutico;
- Operador da farmácia;
- Fornecedor;
- Parceiro responsável pela entrega.

## Sistemas e serviços externos

O PharmaLink também possui comunicação com serviços externos, como:

- serviço de pagamento;
- serviço de entrega;
- fornecedor;
- serviço de comunicação/notificação, como WhatsApp.

## Contexto observado

De forma geral, o sistema deve permitir que um pedido de medicamento passe por etapas relacionadas ao estoque, pagamento, entrega e notificação.

Fluxo inicial identificado:

Cliente → Pedido → Medicamento/Estoque → Pagamento → Entrega → Notificação

## Restrições identificadas

Durante a análise inicial, foram observados alguns pontos que precisam ser considerados pelo sistema:

- alguns medicamentos podem exigir receita;
- medicamentos possuem estoque e lotes;
- os lotes possuem quantidade e validade;
- o sistema depende de serviços externos para algumas operações;
- pagamentos e entregas fazem parte do fluxo do pedido.

## Considerações

Este documento representa apenas o entendimento inicial da equipe sobre o contexto do PharmaLink.

Os atores, integrações, restrições e regras poderão ser detalhados ou revisados durante as próximas aulas conforme o projeto for analisado com maior profundidade.
