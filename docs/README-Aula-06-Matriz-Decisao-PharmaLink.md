# PharmaLink — Matriz de Decisão Arquitetural

## Alternativas analisadas

- **Alternativa A:** manter monólito em camadas simples.
- **Alternativa B:** evoluir para monólito modular em camadas com portas/adapters e eventos internos.
- **Alternativa C:** migrar para microsserviços/SOA separados por domínio.

## Escala utilizada

### Pesos

| Peso | Significado |
|---:|---|
| 1 | Baixa importância |
| 2 | Importância média |
| 3 | Alta importância |

### Notas

| Nota | Significado |
|---:|---|
| 1 | Atende fracamente |
| 2 | Atende parcialmente |
| 3 | Atende de forma adequada |
| 4 | Atende bem |
| 5 | Atende muito bem |

## Matriz

| Critério | Peso | Nota A | P×N A | Nota B | P×N B | Nota C | P×N C |
|---|---:|---:|---:|---:|---:|---:|---:|
| Desempenho | 3 | 3 | 9 | 4 | 12 | 4 | 12 |
| Segurança | 3 | 2 | 6 | 4 | 12 | 5 | 15 |
| Manutenibilidade | 3 | 2 | 6 | 5 | 15 | 4 | 12 |
| Disponibilidade / confiabilidade | 3 | 2 | 6 | 4 | 12 | 5 | 15 |
| Complexidade operacional | 2 | 5 | 10 | 4 | 8 | 1 | 2 |
| Adequação ao estágio do projeto | 2 | 4 | 8 | 5 | 10 | 2 | 4 |
| **TOTAL** |  |  | **45** |  | **69** |  | **60** |

## Conferência dos cálculos

### Alternativa A

`(3×3) + (3×2) + (3×2) + (3×2) + (2×5) + (2×4)`  
`9 + 6 + 6 + 6 + 10 + 8 = 45`

### Alternativa B

`(3×4) + (3×4) + (3×5) + (3×4) + (2×4) + (2×5)`  
`12 + 12 + 15 + 12 + 8 + 10 = 69`

### Alternativa C

`(3×4) + (3×5) + (3×4) + (3×5) + (2×1) + (2×2)`  
`12 + 15 + 12 + 15 + 2 + 4 = 60`

## Resultado

- **Total da Alternativa A:** 45 pontos
- **Total da Alternativa B:** 69 pontos
- **Total da Alternativa C:** 60 pontos

### Alternativa com maior pontuação

**Alternativa B — Monólito modular em camadas com portas/adapters e eventos internos: 69 pontos.**

A pontuação não foi utilizada isoladamente. A decisão também considera custo, risco, complexidade e maturidade do PharmaLink. A Alternativa B melhora as fronteiras do sistema sem exigir múltiplos deploys, comunicação distribuída e infraestrutura de microsserviços.
