package backend.config;

import backend.model.Correntista;
import backend.model.Papel;
import backend.repository.CorrentistaRepository;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class InicializadorAdmin implements ApplicationRunner {

    private final CorrentistaRepository correntistaRepository;

    public InicializadorAdmin(CorrentistaRepository correntistaRepository) {
        this.correntistaRepository = correntistaRepository;
    }

    @Override
    public void run(ApplicationArguments args) {

        if (correntistaRepository.findByLogin("admin").isEmpty()) {

            Correntista admin = new Correntista();

            admin.setNome("Administrador");
            admin.setLogin("admin");
            admin.setSenha("123");
            admin.setPapel(Papel.ADMINISTRADOR);
            admin.setBloqueado(false);

            correntistaRepository.save(admin);

            System.out.println("Administrador inicial criado.");
        }
    }
}