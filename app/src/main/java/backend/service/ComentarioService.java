package backend.service;

import backend.dto.ComentarioForm;
import backend.model.Comentario;
import backend.model.Transacao;
import backend.repository.ComentarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComentarioService {

    private final ComentarioRepository comentarioRepository;

    public ComentarioService(ComentarioRepository comentarioRepository) {
        this.comentarioRepository = comentarioRepository;
    }

    public List<Comentario> listarPorTransacao(Long transacaoId) {
        return comentarioRepository.findByTransacaoIdOrderByIdAsc(transacaoId);
    }

    public Comentario buscarPorId(Long id) {
        return comentarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Comentário não foi encontrado."));
    }

    public Comentario criar(Transacao transacao, ComentarioForm form) {
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