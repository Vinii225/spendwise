package backend.service;

import backend.model.Correntista;
import backend.repository.CorrentistaRepository;
import org.springframework.stereotype.Service;
import backend.dto.CorrentistaForm;
import backend.model.Papel;

import java.util.List;

@Service
public class CorrentistaService {

    private final CorrentistaRepository correntistaRepository;

    public CorrentistaService(CorrentistaRepository correntistaRepository) {
        this.correntistaRepository = correntistaRepository;
    }

    public List<Correntista> listarTodos() {
        return correntistaRepository.findAll();
    }

    public Correntista criar(CorrentistaForm form) {

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
