CREATE TABLE correntistas (
    id         BIGSERIAL PRIMARY KEY,
    nome       VARCHAR(150) NOT NULL,
    login      VARCHAR(50)  NOT NULL UNIQUE,
    senha      VARCHAR(255) NOT NULL,
    papel      VARCHAR(20)  NOT NULL CHECK (papel IN ('CORRENTISTA', 'ADMINISTRADOR')),
    bloqueado  BOOLEAN      NOT NULL DEFAULT FALSE
);

CREATE TABLE contas (
    id              BIGSERIAL PRIMARY KEY,
    numero          VARCHAR(20)  NOT NULL,
    descricao       VARCHAR(150) NOT NULL,
    tipo            VARCHAR(10)  NOT NULL CHECK (tipo IN ('CORRENTE', 'CARTAO')),
    dia_fechamento  INT,
    correntista_id  BIGINT NOT NULL REFERENCES correntistas (id) ON DELETE CASCADE,
    CONSTRAINT dia_fechamento_somente_cartao
        CHECK (tipo = 'CARTAO' OR dia_fechamento IS NULL)
);

CREATE TABLE categorias (
    id        BIGSERIAL PRIMARY KEY,
    nome      VARCHAR(100) NOT NULL,
    ativo     BOOLEAN      NOT NULL DEFAULT TRUE,
    natureza  VARCHAR(15)  NOT NULL CHECK (natureza IN ('ENTRADA', 'SAIDA', 'INVESTIMENTO')),
    ordem     INT          NOT NULL
);

CREATE TABLE transacoes (
    id            BIGSERIAL PRIMARY KEY,
    data          DATE           NOT NULL,
    descricao     VARCHAR(150)   NOT NULL,
    valor         DECIMAL(10, 2) NOT NULL,
    movimento     CHAR(1)        NOT NULL CHECK (movimento IN ('C', 'D')),
    conta_id      BIGINT NOT NULL REFERENCES contas (id) ON DELETE CASCADE,
    categoria_id  BIGINT NOT NULL REFERENCES categorias (id) ON DELETE RESTRICT
);

CREATE TABLE comentarios (
    id            BIGSERIAL PRIMARY KEY,
    texto         TEXT   NOT NULL,
    transacao_id  BIGINT NOT NULL UNIQUE REFERENCES transacoes (id) ON DELETE CASCADE
);
