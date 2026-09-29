INSERT INTO correntistas (nome, login, senha, papel, bloqueado) VALUES
    ('Admin Sistema', 'admin', 'admin123', 'ADMINISTRADOR', FALSE),
    ('João Silva', 'joao.silva', '12345', 'CORRENTISTA', FALSE),
    ('Maria Souza', 'maria.souza', '12345', 'CORRENTISTA', FALSE)
ON CONFLICT (login) DO NOTHING;

INSERT INTO contas (numero, descricao, tipo, dia_fechamento, correntista_id) VALUES
    ('CC-0001', 'Conta corrente principal', 'CORRENTE', NULL, (SELECT id FROM correntistas WHERE login = 'joao.silva')),
    ('CB-0001', 'Cartão de crédito', 'CARTAO', 10, (SELECT id FROM correntistas WHERE login = 'joao.silva')),
    ('CC-0002', 'Conta corrente', 'CORRENTE', NULL, (SELECT id FROM correntistas WHERE login = 'maria.souza')),
    ('CB-0002', 'Cartão de crédito', 'CARTAO', 5, (SELECT id FROM correntistas WHERE login = 'maria.souza'));

-- Conjunto mínimo de categorias predefinidas (docs/db/dicionario-dados.md).
INSERT INTO categorias (nome, ativo, natureza, ordem) VALUES
    ('Salário', TRUE, 'ENTRADA', 1),
    ('Cashback', TRUE, 'ENTRADA', 2),
    ('Resgate Investimento', TRUE, 'ENTRADA', 3),
    ('Outras Entradas', TRUE, 'ENTRADA', 4),
    ('Saúde e Remédios', TRUE, 'SAIDA', 1),
    ('Academia e Personal', TRUE, 'SAIDA', 2),
    ('Carros e Uber', TRUE, 'SAIDA', 3),
    ('Educação e Cursos', TRUE, 'SAIDA', 4),
    ('Lazer e Turismo', TRUE, 'SAIDA', 5),
    ('Condomínio', TRUE, 'SAIDA', 6),
    ('Energia', TRUE, 'SAIDA', 7),
    ('Celular', TRUE, 'SAIDA', 8),
    ('Internet', TRUE, 'SAIDA', 9),
    ('Itens Pessoais', TRUE, 'SAIDA', 10),
    ('Feira', TRUE, 'SAIDA', 11),
    ('Casa', TRUE, 'SAIDA', 12),
    ('Impostos', TRUE, 'SAIDA', 13),
    ('Outros gastos', TRUE, 'SAIDA', 14),
    ('Aporte Renda Fixa', TRUE, 'INVESTIMENTO', 1),
    ('Aporte Renda Variável', TRUE, 'INVESTIMENTO', 2),
    ('Aporte Reserva Emergência', TRUE, 'INVESTIMENTO', 3),
    ('Aporte Previdência', TRUE, 'INVESTIMENTO', 4);

INSERT INTO transacoes (data, descricao, valor, movimento, conta_id, categoria_id) VALUES
    ('2026-09-01', 'Salário de setembro', 5000.00, 'C', (SELECT id FROM contas WHERE numero = 'CC-0001'), (SELECT id FROM categorias WHERE nome = 'Salário')),
    ('2026-09-05', 'Supermercado', 350.90, 'D', (SELECT id FROM contas WHERE numero = 'CC-0001'), (SELECT id FROM categorias WHERE nome = 'Feira')),
    ('2026-09-10', 'Uber', 45.50, 'D', (SELECT id FROM contas WHERE numero = 'CB-0001'), (SELECT id FROM categorias WHERE nome = 'Carros e Uber')),
    ('2026-09-12', 'Cinema', 60.00, 'D', (SELECT id FROM contas WHERE numero = 'CC-0002'), (SELECT id FROM categorias WHERE nome = 'Lazer e Turismo')),
    ('2026-09-15', 'Aplicação CDB', 1000.00, 'D', (SELECT id FROM contas WHERE numero = 'CB-0002'), (SELECT id FROM categorias WHERE nome = 'Aporte Renda Fixa'));

INSERT INTO comentarios (texto, transacao_id) VALUES
    ('Conferir se caiu certinho no contracheque.', (SELECT id FROM transacoes WHERE descricao = 'Salário de setembro')),
    ('Compra do mês, dividida com o colega de apê.', (SELECT id FROM transacoes WHERE descricao = 'Supermercado'));
