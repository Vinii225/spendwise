-- Contas extras só pra ter volume suficiente pra testar paginação
-- (tamanho de página padrão é 10, ver ContaExtratoController.listarContas).
INSERT INTO contas (numero, descricao, tipo, dia_fechamento, correntista_id) VALUES
    ('CC-1001', 'Conta poupança', 'CORRENTE', NULL, (SELECT id FROM correntistas WHERE login = 'joao.silva')),
    ('CC-1002', 'Conta reserva', 'CORRENTE', NULL, (SELECT id FROM correntistas WHERE login = 'joao.silva')),
    ('CC-1003', 'Conta viagem', 'CORRENTE', NULL, (SELECT id FROM correntistas WHERE login = 'joao.silva')),
    ('CC-1004', 'Conta investimentos', 'CORRENTE', NULL, (SELECT id FROM correntistas WHERE login = 'joao.silva')),
    ('CC-1005', 'Conta extra', 'CORRENTE', NULL, (SELECT id FROM correntistas WHERE login = 'joao.silva')),
    ('CB-1001', 'Cartão platinum', 'CARTAO', 5, (SELECT id FROM correntistas WHERE login = 'joao.silva')),
    ('CB-1002', 'Cartão gold', 'CARTAO', 12, (SELECT id FROM correntistas WHERE login = 'joao.silva')),
    ('CB-1003', 'Cartão viagem', 'CARTAO', 20, (SELECT id FROM correntistas WHERE login = 'joao.silva')),
    ('CB-1004', 'Cartão empresarial', 'CARTAO', 1, (SELECT id FROM correntistas WHERE login = 'joao.silva')),
    ('CB-1005', 'Cartão reserva', 'CARTAO', 15, (SELECT id FROM correntistas WHERE login = 'joao.silva'));
