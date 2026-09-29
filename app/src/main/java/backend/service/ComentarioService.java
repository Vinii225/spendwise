package backend.service;

import backend.dto.ComentarioForm;
import backend.model.Comentario;
import backend.model.Transacao;
import backend.repository.ComentarioRepository;
import org.springframework.stereotype.Service;

@Service
public class ComentarioService {

    private final ComentarioRepository comentarioRepository;

    public ComentarioService(ComentarioRepository comentarioRepository) {
        this.comentarioRepository = comentarioRepository;
    }

    public Comentario buscarPorId(Long id) {
        return comentarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Comentário não foi encontrado."));
    }

    public Comentario criar(Transacao transacao, ComentarioForm form) {
        if (comentarioRepository.findByTransacaoId(transacao.getId()).isPresent()) {
            throw new IllegalArgumentException("Esta transação já possui um comentário.");
        }
        Comentario comentario = new Comentario();
        comentario.setTexto(form.getTexto());
        comentario.setTransacao(transacao);
        return comentarioRepository.save(comentario);
    }

    public Comentario editar(Long id, ComentarioForm form) {
        Comentario comentario = buscarPorId(id);
        comentario.setTexto(form.getTexto());
        return comentarioRepository.save(comentario);
    }

    public void excluir(Long id) {
        comentarioRepository.delete(buscarPorId(id));
    }
}