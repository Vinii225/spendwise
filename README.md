# SpendWise – Controle Financeiro na Web

Aplicação web para controle financeiro pessoal. Permite que o usuário registre transações (créditos e débitos) em contas-correntes e cartões de crédito, organizando-as por categorias, e acompanhe extratos e o orçamento anual por categoria.

Projeto desenvolvido para a disciplina **Programação para a Web II**, do curso de **TSI em Sistemas para a Internet**, Instituto Federal da Paraíba (IFPB), sob orientação do **Prof. Frederico Costa Guedes Pereira**.

## Integrantes e Matrícula correspondente

- Maria Laura - 20242370015
- Luana Gabriella - 20242370026
- Vinícius Ares - 20242370007

## Documentação

O escopo completo do projeto está no PDF original ([docs/Projeto SpendWise.pdf](docs/Projeto%20SpendWise.pdf)). Documentação derivada:

- [Requisitos funcionais e não funcionais](docs/Requisitos-SpendWise.pdf) — RF01–RF14 e RNF01–RNF09
- [Checklist de casos de uso](docs/checklist-ucs.md) — UC01–UC09 e UC20–UC24 detalhados, com responsável e status de cada um
- [Dicionário de dados](docs/db/dicionario-dados.md) — inclui os modelos ER e lógico do banco

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
