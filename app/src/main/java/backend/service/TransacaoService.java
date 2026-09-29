package backend.service;

import backend.dto.TransacaoForm;
import backend.model.Categoria;
import backend.model.Conta;
import backend.model.Correntista;
import backend.model.Transacao;
import backend.repository.CategoriaRepository;
import backend.repository.ContaRepository;
import backend.repository.TransacaoRepository;
import org.springframework.stereotype.Service;

@Service
public class TransacaoService {

    private final TransacaoRepository transacaoRepository;
    private final ContaRepository contaRepository;
    private final CategoriaRepository categoriaRepository;

    public TransacaoService(TransacaoRepository transacaoRepository, ContaRepository contaRepository, CategoriaRepository categoriaRepository) {

        this.transacaoRepository = transacaoRepository;
        this.contaRepository = contaRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public Transacao criar(TransacaoForm form, Long contaId, Correntista correntista) {

        if (correntista.isBloqueado()) {
            throw new IllegalArgumentException("Correntista bloqueado.");
        }

        Conta conta = contaRepository.findById(contaId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Conta não encontrada."));

        if (!conta.getCorrentista().getId().equals(correntista.getId())) {
            throw new IllegalArgumentException(
                    "Conta não pertence ao correntista."
            );
        }

        Categoria categoria = categoriaRepository
                .findById(form.getCategoriaId())
                .orElseThrow(() ->
                        new IllegalArgumentException("Categoria não encontrada."));

        Transacao transacao = new Transacao();

        transacao.setData(form.getData());
        transacao.setDescricao(form.getDescricao());
        transacao.setValor(form.getValor());
        transacao.setMovimento(form.getMovimento());

        transacao.setConta(conta);
        transacao.setCategoria(categoria);

        return transacaoRepository.save(transacao);
    }
}