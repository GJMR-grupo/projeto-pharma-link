# PharmaLink — Justificativas das Notas da Matriz

Todas as notas abaixo são justificadas com base na estrutura e nas limitações observadas no próprio projeto PharmaLink.

| Critério | Alternativa | Nota | Justificativa | Evidência no projeto |
|---|---|---:|---|---|
| Desempenho | A | 3 | O fluxo é simples e local, porém pagamento, entrega e WhatsApp são chamados diretamente no serviço principal e podem aumentar o tempo da operação. | `PharmacyApplicationService` coordena as integrações no mesmo fluxo. |
| Desempenho | B | 4 | Portas/adapters isolam integrações e eventos internos permitem retirar notificação e auditoria do caminho crítico. | O projeto já possui `patterns/adapter`, que pode ser usado como base para as fronteiras. |
| Desempenho | C | 4 | Serviços independentes permitem escalar partes específicas, mas a comunicação por rede adiciona latência e novos pontos de falha. | O repositório atual não possui contratos ou comunicação entre serviços. |
| Segurança | A | 2 | O fluxo atual permite continuar quando falta receita e possui controle insuficiente entre pagamento e despacho. | `addItem()` apenas gera aviso; `finishOrder()` chama a entrega no fluxo atual. |
| Segurança | B | 4 | Regras podem ser centralizadas no domínio e pagamento pode ser isolado por interface, reduzindo acoplamento e facilitando validação. | `Medicine.prescriptionRequired` e os adapters existentes fornecem pontos de evolução. |
| Segurança | C | 5 | A separação por serviços permitiria controles independentes para pedidos, receitas e pagamentos, embora exigisse autenticação e governança entre serviços. | Os domínios de pedido, receita e pagamento já são identificáveis no código. |
| Manutenibilidade | A | 2 | Mantém muitas responsabilidades em uma única classe de aplicação. | `PharmacyApplicationService` reúne estoque, receita, desconto, pagamento, entrega, notificação e eventos. |
| Manutenibilidade | B | 5 | Divide responsabilidades sem separar o sistema em vários deploys, permitindo refatoração incremental. | Já existem pacotes `model`, `repository`, `service`, `legacy` e `patterns`. |
| Manutenibilidade | C | 4 | A separação é forte, porém aumenta a manutenção de contratos, serviços, deploys e comunicação. | O projeto atual não possui infraestrutura de microsserviços. |
| Disponibilidade / confiabilidade | A | 2 | Integrações estão acopladas ao fluxo e o mecanismo atual de observer pode perder um dos comportamentos esperados. | `OrderPublisher` mantém um observer; o segundo `subscribe()` substitui o primeiro. |
| Disponibilidade / confiabilidade | B | 4 | Eventos internos podem desacoplar notificação e auditoria do fluxo principal e permitir tratamento específico de falhas. | `patterns/observer` já fornece uma base conceitual para evolução. |
| Disponibilidade / confiabilidade | C | 5 | Serviços independentes oferecem maior isolamento de falhas, mas exigem observabilidade e tratamento distribuído. | O sistema atual é local e não possui mensageria ou monitoramento distribuído. |
| Complexidade operacional | A | 5 | É a opção com menor esforço operacional, pois mantém uma única aplicação simples. | O sistema é executado a partir de `Main` e possui scripts de compilação. |
| Complexidade operacional | B | 4 | Adiciona interfaces, serviços menores e eventos internos, mas continua sendo uma única aplicação Java. | Não exige múltiplos deploys nem infraestrutura distribuída. |
| Complexidade operacional | C | 1 | Exigiria rede, monitoramento, contratos, persistência e implantação de vários serviços. | Esses elementos não existem no projeto fornecido. |
| Adequação ao estágio do projeto | A | 4 | É simples e compatível com um projeto acadêmico inicial, mas não resolve os riscos levantados. | A estrutura atual executa, porém apresenta acoplamento e responsabilidades concentradas. |
| Adequação ao estágio do projeto | B | 5 | Evolui exatamente os pontos problemáticos sem introduzir tecnologia desnecessária. | A estrutura atual em pacotes e os adapters permitem evolução incremental. |
| Adequação ao estágio do projeto | C | 2 | É uma possibilidade futura, mas precoce para o tamanho e a maturidade atuais. | Não existem API REST real, bancos independentes, contratos de serviço ou automação de deploy. |

## Síntese das justificativas

A **Alternativa A** preserva simplicidade, mas mantém os principais riscos arquiteturais encontrados.

A **Alternativa B** apresenta o melhor equilíbrio para o estágio atual: melhora manutenção, segurança e confiabilidade, preservando uma única aplicação.

A **Alternativa C** oferece forte isolamento e potencial de escala, mas cobra um custo operacional muito maior do que o PharmaLink necessita atualmente.

Por isso, a matriz resulta em **45 pontos para A, 69 para B e 60 para C**, sustentando a escolha da **Alternativa B**.
