-- Transações extras cobrindo o mês atual (além de setembro) e as contas que
-- ainda estavam sem nenhum lançamento, para dar volume real ao extrato e
-- ao saldo por período.
INSERT INTO transacoes (data, descricao, valor, movimento, conta_id, categoria_id) VALUES
    ('2026-10-01', 'Salário de outubro', 5200.00, 'C', (SELECT id FROM contas WHERE numero = 'CC-0001'), (SELECT id FROM categorias WHERE nome = 'Salário')),
    ('2026-10-03', 'Supermercado', 410.75, 'D', (SELECT id FROM contas WHERE numero = 'CC-0001'), (SELECT id FROM categorias WHERE nome = 'Feira')),
    ('2026-10-04', 'Conta de energia', 180.20, 'D', (SELECT id FROM contas WHERE numero = 'CC-0001'), (SELECT id FROM categorias WHERE nome = 'Energia')),
    ('2026-10-06', 'Cashback cartão', 25.00, 'C', (SELECT id FROM contas WHERE numero = 'CC-0001'), (SELECT id FROM categorias WHERE nome = 'Cashback')),

    ('2026-10-02', 'Uber para o trabalho', 38.90, 'D', (SELECT id FROM contas WHERE numero = 'CB-0001'), (SELECT id FROM categorias WHERE nome = 'Carros e Uber')),
    ('2026-10-05', 'Academia', 99.90, 'D', (SELECT id FROM contas WHERE numero = 'CB-0001'), (SELECT id FROM categorias WHERE nome = 'Academia e Personal')),
    ('2026-09-20', 'Internet', 120.00, 'D', (SELECT id FROM contas WHERE numero = 'CB-0001'), (SELECT id FROM categorias WHERE nome = 'Internet')),

    ('2026-10-01', 'Salário de outubro', 3800.00, 'C', (SELECT id FROM contas WHERE numero = 'CC-0002'), (SELECT id FROM categorias WHERE nome = 'Salário')),
    ('2026-10-04', 'Celular', 89.90, 'D', (SELECT id FROM contas WHERE numero = 'CC-0002'), (SELECT id FROM categorias WHERE nome = 'Celular')),
    ('2026-10-07', 'Curso online', 250.00, 'D', (SELECT id FROM contas WHERE numero = 'CC-0002'), (SELECT id FROM categorias WHERE nome = 'Educação e Cursos')),

    ('2026-10-02', 'Resgate de investimento', 500.00, 'C', (SELECT id FROM contas WHERE numero = 'CB-0002'), (SELECT id FROM categorias WHERE nome = 'Resgate Investimento')),
    ('2026-10-09', 'Impostos', 310.00, 'D', (SELECT id FROM contas WHERE numero = 'CB-0002'), (SELECT id FROM categorias WHERE nome = 'Impostos')),

    ('2026-09-25', 'Depósito na poupança', 400.00, 'D', (SELECT id FROM contas WHERE numero = 'CC-1001'), (SELECT id FROM categorias WHERE nome = 'Aporte Reserva Emergência')),
    ('2026-10-01', 'Rendimento poupança', 12.50, 'C', (SELECT id FROM contas WHERE numero = 'CC-1001'), (SELECT id FROM categorias WHERE nome = 'Resgate Investimento')),

    ('2026-10-03', 'Aporte reserva de emergência', 300.00, 'D', (SELECT id FROM contas WHERE numero = 'CC-1002'), (SELECT id FROM categorias WHERE nome = 'Aporte Reserva Emergência')),

    ('2026-09-18', 'Passagem aérea', 890.00, 'D', (SELECT id FROM contas WHERE numero = 'CC-1003'), (SELECT id FROM categorias WHERE nome = 'Lazer e Turismo')),
    ('2026-10-05', 'Hospedagem viagem', 450.00, 'D', (SELECT id FROM contas WHERE numero = 'CC-1003'), (SELECT id FROM categorias WHERE nome = 'Lazer e Turismo')),

    ('2026-10-01', 'Aporte renda variável', 600.00, 'D', (SELECT id FROM contas WHERE numero = 'CC-1004'), (SELECT id FROM categorias WHERE nome = 'Aporte Renda Variável')),
    ('2026-10-02', 'Dividendos recebidos', 75.30, 'C', (SELECT id FROM contas WHERE numero = 'CC-1004'), (SELECT id FROM categorias WHERE nome = 'Outras Entradas')),

    ('2026-10-04', 'Compra diversa', 60.00, 'D', (SELECT id FROM contas WHERE numero = 'CC-1005'), (SELECT id FROM categorias WHERE nome = 'Itens Pessoais')),

    ('2026-10-03', 'Anuidade cartão platinum', 150.00, 'D', (SELECT id FROM contas WHERE numero = 'CB-1001'), (SELECT id FROM categorias WHERE nome = 'Outros gastos')),

    ('2026-10-06', 'Compra em loja', 220.00, 'D', (SELECT id FROM contas WHERE numero = 'CB-1002'), (SELECT id FROM categorias WHERE nome = 'Itens Pessoais')),

    ('2026-09-28', 'Pacote de viagem', 1200.00, 'D', (SELECT id FROM contas WHERE numero = 'CB-1003'), (SELECT id FROM categorias WHERE nome = 'Lazer e Turismo')),

    ('2026-10-02', 'Despesas da casa', 340.00, 'D', (SELECT id FROM contas WHERE numero = 'CB-1004'), (SELECT id FROM categorias WHERE nome = 'Casa')),

    ('2026-10-05', 'Compra reserva', 80.00, 'D', (SELECT id FROM contas WHERE numero = 'CB-1005'), (SELECT id FROM categorias WHERE nome = 'Outros gastos'));
