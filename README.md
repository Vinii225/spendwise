# SpendWise – Controle Financeiro na Web

Aplicação web para controle financeiro pessoal. Permite que o usuário registre transações (créditos e débitos) em contas-correntes e cartões de crédito, organizando-as por categorias, e acompanhe extratos e o orçamento anual por categoria.

Projeto desenvolvido para a disciplina **Programação para a Web II**, do curso de **TSI em Sistemas para a Internet**, Instituto Federal da Paraíba (IFPB), sob orientação do **Prof. Frederico Costa Guedes Pereira**.

## Integrantes e Matrícula correspondente

- Maria Laura - 20242370015
- Luana Gabriella - 20242370026
- Vinícius Ares - 20242370007

## Documentação

O escopo completo do projeto está no PDF original ([docs/projeto-spendwise.pdf](docs/projeto-spendwise.pdf)). Documentação derivada:

- [Requisitos funcionais e não funcionais](docs/requisitos-spendwise.pdf) — RF01–RF14 e RNF01–RNF09
- Checklist de casos de uso, com responsável e status de cada um:
  - [Etapa I](docs/etapa1/checklist-ucs-etapa1.md) — UC21, UC20 e UC01–UC06
  - [Etapa II](docs/etapa2/checklist-ucs-etapa2.md) — UC23, UC22, UC24 e UC07–UC09
- [Dicionário de dados](db/docs/dicionario-dados.md) — inclui os modelos ER e lógico do banco

## Estrutura do repositório

```text
.
├── backend/    # aplicação Java/Groovy (Gradle)
├── frontend/   
├── docs/       # documentação do projeto
└── docker-compose.yml
```

## Como rodar

### Estruturas Gradle

```bash
cd backend
./gradlew build
./gradlew run
```

### Docker

```bash
docker compose up --build
```

Para limpar o ambiente (containers, volumes e imagens gerados pelo build):

```bash
docker compose down -v
```

> Configuração do `docker-compose.yml` e dos `Dockerfiles` ainda em andamento devido ao estágio de desenvolvimento da aplicação.
