# PharmaLink — Projeto Semestral

O PharmaLink simula uma farmácia digital que trabalha com medicamentos, estoque, lotes, fornecedores, pedidos, pagamento, entrega e integrações externas.

Este repositório representa um **sistema legado em evolução**. O código inicial executa, mas contém decisões incompletas, inconsistências e implementações deliberadamente questionáveis. Durante o semestre, o aluno deverá analisar, justificar e modificar o projeto conforme os conceitos apresentados em aula.

## Escopo inicial
- cadastro simplificado de medicamentos;
- controle básico de estoque e lotes;
- criação de pedidos;
- aplicação de descontos;
- pagamento;
- integração com fornecedor;
- entrega;
- notificações por e-mail, SMS e WhatsApp;
- verificação simplificada de receita.

## Execução
Requer Java 17.

```bash
javac -d out $(find src/main/java -name "*.java")
java -cp out br.edu.pharmalink.Main
```

No Windows, o aluno pode usar o script `scripts/compile.ps1`.

## Atividades
Os arquivos `atividades/README_PharmaLink_AulaXX.md` orientam a evolução de cada aula.

> A existência de classes denominadas `Factory`, `Adapter`, `Strategy`, `Observer` ou `Facade` não significa que os padrões estejam corretamente aplicados. O aluno deve analisar problema, necessidade, implementação e consequências.
