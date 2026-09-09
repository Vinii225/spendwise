# Checklist de Casos de Uso — SpendWise

> Fonte: [`Projeto SpendWise.pdf`](./Projeto%20SpendWise.pdf)


## Correntista

---

### UC01 — Correntista cadastra conta

`10 pts`

- [ ] Concluído — Responsável: ____________

> **Pré-condição**
>
> Correntista não tem conta criada.
>
> **Fluxo principal**
>
> 1. Correntista acessa o site e clica em "criar conta".
> 2. Sistema exibe formulário com dados para criação da conta.
> 3. Correntista preenche formulário e o envia.
> 4. Sistema cria registro para correntista, ou faz a crítica de dados incorretos (login já existe na base).
> 5. Fim.
>
> **Pós-condição**
>
> Um novo correntista existe no sistema.

---

### UC02 — Correntista acessar contas

`10 pts`

- [ ] Concluído — Responsável: ____________

> **Pré-condição**
>
> O administrador criou conta(s) para o usuário.
>
> **Fluxo principal**
>
> 1. Correntista acessa o site e clica em listar contas.
> 2. Sistema exibe contas separadas ou todas juntas, com destaque para o tipo.
> 3. Fim.
>
> **Pós-condição**
>
> Nenhuma.

---

### UC03 — Correntista cria transação para conta

`15 pts`

- [ ] Concluído — Responsável: ____________

> **Pré-condição**
>
> Conta existente e correntista não bloqueado.
>
> **Fluxo principal**
>
> 1. Correntista acessa a conta em que deseja criar a transação.
> 2. Correntista clica em botão/ícone para adicionar transação.
> 3. Sistema apresenta formulário com os dados a serem coletados.
> 4. Correntista fornece os dados e clica em "Salvar".
> 5. Fim.
>
> **Pós-condição**
>
> A conta possui uma nova transação.

---

### UC04 — Correntista edita transação existente

`15 pts`

- [ ] Concluído — Responsável: ____________

> **Pré-condição**
>
> Existir transações na conta.
>
> **Fluxo principal**
>
> 1. Correntista acessa a conta.
> 2. Correntista acessa a transação e pede para editá-la.
> 3. Sistema apresenta formulário com dados atuais da transação.
> 4. Correntista modifica os dados e clica em "Salvar".
> 5. Sistema modifica a transação em banco.
> 6. Fim.
>
> **Pós-condição**
>
> A transação é modificada com os dados fornecidos.

---

### UC05 — Correntista adiciona comentário à transação

`10 pts`

- [ ] Concluído — Responsável: ____________

> **Pré-condição**
>
> Existir transações na conta.
>
> **Fluxo principal**
>
> 1. Correntista acessa a conta.
> 2. Correntista acessa a transação e pede para adicionar comentário.
> 3. Sistema pede o texto do comentário em formulário específico.
> 4. Correntista informa o texto do comentário e clica em "Salvar".
> 5. Sistema adiciona o comentário à transação.
> 6. Fim.
>
> **Pós-condição**
>
> A transação possui comentário associado.

---

### UC06 — Correntista edita/exclui comentário

`10 pts`

- [ ] Concluído — Responsável: ____________

> **Pré-condição**
>
> Comentário existe para uma transação.
>
> **Fluxo principal**
>
> 1. Correntista acessa a conta.
> 2. Correntista acessa a transação.
> 3. Correntista pede para editar o comentário da transação OU excluí-lo.
> 4. *[Para edição]* Sistema apresenta o texto atual do comentário.
> 5. *[Para edição]* Correntista modifica o texto e clica em "Salvar".
> 6. Sistema aplica a modificação/exclusão no comentário.
> 7. Fim.
>
> **Pós-condição**
>
> O comentário deve ser modificado OU excluído.

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

### UC20 — Administrador acessa listagem de correntistas

`10 pts`

- [ ] Concluído — Responsável: ____________

> **Pré-condição**
>
> Estar autenticado no sistema (login).
>
> **Fluxo principal**
>
> 1. Administrador pede ao sistema a listagem de todos os correntistas.
> 2. Sistema exibe tabela com informações dos correntistas cadastrados (ex: nome).
> 3. Fim.

---

### UC21 — Administrador cadastra correntista

`10 pts`

- [ ] Concluído — Responsável: ____________

> **Pré-condição**
>
> Nenhuma.
>
> **Fluxo principal**
>
> 1. Administrador pede listagem de correntistas cadastrados.
> 2. Sistema exibe listagem em tabela.
> 3. Administrador pede para criar correntista.
> 4. Sistema apresenta formulário para fornecimento dos dados do correntista.
> 5. Administrador preenche formulário e clica em "Salvar".
> 6. Sistema registra o novo correntista.
> 7. Fim.
>
> **Pós-condição**
>
> Um novo correntista existe no sistema.

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
