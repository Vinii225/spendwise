# Checklist de Casos de Uso — SpendWise (Etapa I)

> Fonte: [`projeto-spendwise.pdf`](../projeto-spendwise.pdf)
>
> Pontuação da etapa (Cronograma): UC21 + UC20 + UC01 a UC06 + RNF 07 (P-R-G) = **100 pts**

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
