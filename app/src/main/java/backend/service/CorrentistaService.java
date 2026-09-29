package backend.service;

import backend.model.Correntista;
import backend.repository.CorrentistaRepository;
import org.springframework.stereotype.Service;
import backend.dto.CorrentistaForm;
import backend.model.Papel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class CorrentistaService {

    private final CorrentistaRepository correntistaRepository;

    public CorrentistaService(CorrentistaRepository correntistaRepository) {
        this.correntistaRepository = correntistaRepository;
    }

    public Page<Correntista> listarTodos(Pageable pageable) {
        return correntistaRepository.findAll(pageable);
    }

    public Correntista criar(CorrentistaForm form) {
        if (correntistaRepository.existsByLogin(form.getLogin())) {
            throw new IllegalArgumentException("Este login já está cadastrado.");
        }

        Correntista correntista = new Correntista();

        correntista.setNome(form.getNome());
        correntista.setLogin(form.getLogin());
        correntista.setSenha(form.getSenha());

        correntista.setPapel(Papel.CORRENTISTA);
        correntista.setBloqueado(false);

        return correntistaRepository.save(correntista);
    }

    public Correntista autenticar(String login, String senha) {

        Correntista correntista = correntistaRepository
                .findByLogin(login)
                .orElse(null);

        if (correntista == null) {
            return null;
        }

        if (!correntista.getSenha().equals(senha)) {
            return null;
        }

        if (correntista.isBloqueado()) {
            return null;
        }

        return correntista;
    }

}
