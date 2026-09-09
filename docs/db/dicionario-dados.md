# Dicionário de Dados — SpendWise

Descrição detalhada de cada tabela e coluna do [modelo lógico](./modelo-logico.md), com o significado de negócio de cada campo conforme [`../Projeto SpendWise.pdf`](../Projeto%20SpendWise.pdf).

## correntistas

Representa os usuários do sistema, tanto correntistas comuns quanto administradores (diferenciados pelo campo `papel`).

| Campo     | Tipo         | Nulo? | Chave | Descrição                                                                                                                 |
| --------- | ------------ | ----- | ----- | ------------------------------------------------------------------------------------------------------------------------- |
| id        | bigint       | Não   | PK    | Identificador único do usuário.                                                                                           |
| nome      | varchar(150) | Não   |       | Nome completo do usuário.                                                                                                 |
| login     | varchar(50)  | Não   | UQ    | Identificador de acesso ao sistema. Deve ser único (UC01: "faz a crítica de dados incorretos [login já existe na base]"). |
| senha     | varchar(255) | Não   |       | Hash da senha do usuário, usado na autenticação (RNF 08).                                                                 |
| papel     | varchar(20)  | Não   |       | Define o nível de acesso: `CORRENTISTA` ou `ADMINISTRADOR` (RNF 09 - autorização).                                        |
| bloqueado | boolean      | Não   |       | Indica se o usuário foi bloqueado pelo administrador (UC24). Usuário bloqueado não pode mais entrar no sistema.           |

## contas

Representa as contas-correntes e cartões de crédito de um correntista.

| Campo          | Tipo         | Nulo? | Chave | Descrição                                                                                                                  |
| -------------- | ------------ | ----- | ----- | -------------------------------------------------------------------------------------------------------------------------- |
| id             | bigint       | Não   | PK    | Identificador único da conta.                                                                                              |
| numero         | varchar(20)  | Não   |       | Número identificador da conta (ex: "3212-1").                                                                              |
| descricao      | varchar(150) | Não   |       | Descrição livre da conta (ex: "Minha conta no BB").                                                                        |
| tipo           | varchar(10)  | Não   |       | Tipo da conta: `CORRENTE` ou `CARTAO`.                                                                                     |
| dia_fechamento | int          | Sim   |       | Dia do mês em que o cartão fecha a fatura. Preenchido **somente** quando `tipo = CARTAO`.                                  |
| correntista_id | bigint       | Não   | FK    | Referencia o correntista dono da conta (`correntistas.id`). `ON DELETE CASCADE`: excluir o correntista exclui suas contas. |

## categorias

Classificação das transações. Um conjunto mínimo já vem predefinido pelo sistema; o administrador pode criar novas ou desativar as existentes, nunca excluir.

| Campo    | Tipo         | Nulo? | Chave | Descrição                                                                                                                      |
| -------- | ------------ | ----- | ----- | ------------------------------------------------------------------------------------------------------------------------------ |
| id       | bigint       | Não   | PK    | Identificador único da categoria.                                                                                              |
| nome     | varchar(100) | Não   |       | Nome da categoria (ex: "Saúde e Remédios", "Salário").                                                                         |
| ativo    | boolean      | Não   |       | Indica se a categoria está disponível para uso. Categorias nunca são excluídas, apenas desativadas.                            |
| natureza | varchar(15)  | Não   |       | Classifica a categoria como `ENTRADA`, `SAIDA` ou `INVESTIMENTO`. Define em qual bloco a categoria aparece no orçamento anual. |
| ordem    | int          | Não   |       | Ordem de exibição da categoria dentro da sua natureza, usada na planilha de orçamento anual (UC08).                            |

## transacoes

Registro de um crédito ou débito em uma conta, associado a uma categoria.

| Campo        | Tipo          | Nulo? | Chave | Descrição                                                                                                 |
| ------------ | ------------- | ----- | ----- | --------------------------------------------------------------------------------------------------------- |
| id           | bigint        | Não   | PK    | Identificador único da transação.                                                                         |
| data         | date          | Não   |       | Data em que a transação ocorreu (ex: 10/02/2025).                                                         |
| descricao    | varchar(150)  | Não   |       | Descrição da transação (ex: "Pagamento do curso de desenho").                                             |
| valor        | decimal(10,2) | Não   |       | Valor monetário da transação (ex: 119,00).                                                                |
| movimento    | char(1)       | Não   |       | Indica se a transação é crédito (`C`) ou débito (`D`).                                                    |
| conta_id     | bigint        | Não   | FK    | Referencia a conta em que a transação foi registrada (`contas.id`). `ON DELETE CASCADE`.                  |
| categoria_id | bigint        | Não   | FK    | Referencia a categoria da transação (`categorias.id`). `ON DELETE RESTRICT` (categoria nunca é excluída). |

## comentarios

Comentário opcional associado a uma transação, usado para dar mais detalhes além da descrição e categoria.

| Campo        | Tipo   | Nulo? | Chave  | Descrição                                                                                                                       |
| ------------ | ------ | ----- | ------ | ------------------------------------------------------------------------------------------------------------------------------- |
| id           | bigint | Não   | PK     | Identificador único do comentário.                                                                                              |
| texto        | text   | Não   |        | Texto do comentário (ex: "Recebido 1/3 da herança deixada pela Tia Cotinha para os sobrinhos").                                 |
| transacao_id | bigint | Não   | FK, UQ | Referencia a transação comentada (`transacoes.id`). `UNIQUE` garante no máximo 1 comentário por transação. `ON DELETE CASCADE`. |

## Domínios (valores possíveis)

| Campo                  | Valores válidos                    | Observação                                                                                 |
| ---------------------- | ---------------------------------- | ------------------------------------------------------------------------------------------ |
| `correntistas.papel`   | `CORRENTISTA`, `ADMINISTRADOR`     | Define as permissões do usuário no sistema.                                                |
| `contas.tipo`          | `CORRENTE`, `CARTAO`               | Cartão possui `dia_fechamento`; conta corrente não.                                        |
| `categorias.natureza`  | `ENTRADA`, `SAIDA`, `INVESTIMENTO` | Ordem de exibição no orçamento anual: primeiro ENTRADA, depois SAIDA, depois INVESTIMENTO. |
| `transacoes.movimento` | `C`, `D`                           | Crédito ou débito.                                                                         |

## Categorias predefinidas

Conjunto mínimo que o sistema deve trazer já cadastrado (natureza entre parênteses):

- **Entrada (E):** Salário, Cashback, Resgate Investimento, Outras Entradas
- **Saída (S):** Saúde e Remédios, Academia e Personal, Carros e Uber, Educação e Cursos, Lazer e Turismo, Condomínio, Energia, Celular, Internet, Itens Pessoais, Feira, Casa, Impostos, Outros gastos
- **Investimento (I):** Aporte Renda Fixa, Aporte Renda Variável, Aporte Reserva Emergência, Aporte Previdência
