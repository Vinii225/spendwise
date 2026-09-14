# Checklist de Casos de Uso — SpendWise (Etapa II)

> Fonte: [`projeto-spendwise.pdf`](../projeto-spendwise.pdf)
>
> Pontuação da etapa (Cronograma): UC23 + UC22 + UC24 + UC07 + UC08 + RNF 05 + RNF 07 + RNF 08 + RNF 09 = **100 pts** (+ UC09 opcional = **115 pts**)

## Correntista

---

### UC07 — Correntista consulta extrato de conta

`15 pts`

- [ ] Concluído — Responsável: ____________

> **Pré-condição**
>
> A conta deve existir e possuir transações.
>
> **Fluxo principal**
>
> 1. Correntista acessa a conta.
> 2. Correntista pede ao sistema para exibir o extrato da conta.
> 3. Sistema exibe o extrato do mês corrente na forma tabular, uma linha por transação, com destaque para as que possuem comentários (link para o texto do comentário).
> 4. Fim.
>
> **Fluxo alternativo**
>
> 1. Correntista indica data inicial e data final do período do extrato.
> 2. Sistema exibe o extrato do mesmo modo do fluxo principal.
> 3. Fim.
>
> **Pós-condição**
>
> Não há.

---

### UC08 — Correntista consulta orçamento anual

`15 pts`

- [ ] Concluído — Responsável: ____________

> **Pré-condição**
>
> A conta deve existir e possuir transações em pelo menos 1 dos meses do ano.
>
> **Fluxo principal**
>
> 1. Correntista acessa a opção para gerar o orçamento anual.
> 2. Sistema exibe o orçamento anual do ano corrente: planilha com categorias nas linhas e meses nas colunas. Cada célula soma os valores das transações daquela categoria naquele mês; uma coluna de total soma o ano inteiro. Categorias exibidas separadas por natureza (E, S, I nessa ordem), e dentro de cada natureza pelo campo `ordem`.
> 3. Correntista pode escolher outro ano em campo selecionável.
> 4. Sistema repete o passo 2 para o ano selecionado.
> 5. Fim.
>
> **Pós-condição**
>
> Nenhuma.

---

### UC09 — Correntista consulta orçamento anual em forma de gráfico

`15 pts` · opcional (para ninjas)

- [ ] Concluído — Responsável: ____________

> **Pré-condição**
>
> A conta deve existir e possuir transações em pelo menos 1 dos meses do ano.
>
> **Fluxo principal**
>
> 1. Correntista acessa a opção para gerar o gráfico do orçamento anual.
> 2. Correntista seleciona o ano (corrente é o padrão).
> 3. Sistema exibe gráfico de linhas — cada linha é uma categoria; eixo Y é o valor da categoria no mês, eixo X são os meses.
> 4. Correntista pode ligar/desligar categorias no gráfico.
> 5. Fim.
>
> **Pós-condição**
>
> Não há.
>
> **Observação:** este caso de uso vale o dobro da pontuação do UC07 e não é obrigatório.

## Administrador

---

### UC22 — Administrador lista categorias

`10 pts`

- [ ] Concluído — Responsável: ____________

> **Pré-condição**
>
> Nenhuma.
>
> **Fluxo principal**
>
> 1. Administrador pede listagem de categorias cadastradas.
> 2. Sistema exibe listagem em tabela.
> 3. Fim.
>
> **Pós-condição**
>
> Nenhuma.

---

### UC23 — Administrador cadastra/modifica categoria

`10 pts`

- [ ] Concluído — Responsável: ____________

> **Pré-condição**
>
> Para modificação, a categoria deve existir.
>
> **Fluxo principal**
>
> 1. Administrador acessa categorias cadastradas.
> 2. Administrador solicita cadastro de nova categoria.
> 3. Sistema apresenta formulário para coleta dos dados da categoria.
> 4. Administrador informa os dados e clica em "Salvar".
> 5. Sistema salva a nova categoria no banco.
> 6. Fim.
>
> **Fluxo alternativo**
>
> 1. Administrador solicita modificação de categoria existente.
> 2. Sistema apresenta formulário com os dados da categoria selecionada.
> 3. Administrador informa novos dados e clica em "Salvar".
> 4. Sistema atualiza a categoria.
> 5. Fim.
>
> **Pós-condição**
>
> Uma nova categoria é criada/modificada no sistema.

---

### UC24 — Administrador bloqueia correntista

`10 pts`

- [ ] Concluído — Responsável: ____________

> **Pré-condição**
>
> Correntista deve existir.
>
> **Fluxo principal**
>
> 1. Administrador acessa a listagem de correntistas.
> 2. Administrador pede o bloqueio de um determinado correntista.
> 3. Sistema bloqueia o correntista.
> 4. Fim.
>
> **Pós-condição**
>
> Correntista bloqueado não pode mais usar o sistema.
