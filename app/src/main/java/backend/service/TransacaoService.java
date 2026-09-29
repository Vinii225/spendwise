package backend.service;

import org.springframework.stereotype.Service;

import backend.dto.TransacaoForm;
import backend.model.Categoria;
import backend.model.Transacao;
import backend.repository.CategoriaRepository;
import backend.repository.TransacaoRepository;

@Service
public class TransacaoService {

    private final TransacaoRepository transacaoRepository;
    private final CategoriaRepository categoriaRepository;

    public TransacaoService(TransacaoRepository transacaoRepository,
            CategoriaRepository categoriaRepository) {
        this.transacaoRepository = transacaoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public Transacao buscarPorId(Long id) {
        return transacaoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Transação não foi encontrada."));
    }

    public Transacao editar(Long id, TransacaoForm form) {
        Transacao transacao = buscarPorId(id);
        Categoria categoria = categoriaRepository.findById(form.getCategoriaId())
                .orElseThrow(() -> new IllegalArgumentException("Categoria não foi encontrada."));

        transacao.setData(form.getData());
        transacao.setDescricao(form.getDescricao());
        transacao.setValor(form.getValor());
        transacao.setMovimento(form.getMovimento());
        transacao.setCategoria(categoria);

        return transacaoRepository.save(transacao);
    }
}