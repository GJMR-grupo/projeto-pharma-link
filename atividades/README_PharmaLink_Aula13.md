# PharmaLink — Aula 13

## Tema
Integrações, implantação, Adapter e Facade

## Situação do projeto
O PharmaLink contém decisões e artefatos legados que podem estar incompletos, inconsistentes ou inadequados ao conteúdo desta aula. O aluno deve analisar o estado atual antes de modificar.

## O que o aluno deve fazer
1. explicitar contratos das integrações com fornecedor, pagamento e entrega;
2. analisar `SupplierAdapter`, `PaymentAdapter` e `PharmaLinkFacade` e identificar vazamentos de detalhes externos;
3. corrigir ou substituir Adapter e Facade onde houver problema pertinente, produzindo código, UML/Mermaid e ADR;
4. propor topologia de implantação considerando falhas, segurança e desempenho.

## Dicas
- Adapter deve proteger o domínio das peculiaridades externas.
- Facade não deve expor os subsistemas internos indiscriminadamente.

## Evidências mínimas da entrega
- Issue identificada como `Aula 13`.
- Branch sugerida: `aula-13`.
- Commits com mensagens que expliquem mudanças relevantes.
- README/Markdown da entrega contendo **problema observado, alteração realizada e critério de aceitação**.
- Quando houver código, incluir evidência de compilação/execução ou teste pertinente.
- PR ou registro equivalente permitindo revisão do grupo, quando aplicável.

## Prazo operacional
O trabalho deve começar em sala. O limite de conclusão é o início da aula seguinte, salvo orientação diferente do professor.
