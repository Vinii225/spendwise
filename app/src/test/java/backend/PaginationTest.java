package backend;

import backend.model.Conta;
import backend.model.Correntista;
import backend.model.Papel;
import backend.model.TipoConta;
import backend.repository.ContaRepository;
import backend.repository.CorrentistaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class PaginationTest {

    @Autowired
    private CorrentistaRepository correntistaRepository;

    @Autowired
    private ContaRepository contaRepository;

    @Test
    void paginaCorrentistasNoBanco() {
        for (int indice = 0; indice < 5; indice++) {
            correntistaRepository.save(novoCorrentista("pagination-user-" + indice));
        }

        Page<Correntista> primeiraPagina = correntistaRepository.findAll(
                PageRequest.of(0, 2, Sort.by("id").ascending()));
        Page<Correntista> segundaPagina = correntistaRepository.findAll(
                PageRequest.of(1, 2, Sort.by("id").ascending()));

        assertThat(primeiraPagina.getTotalPages()).isGreaterThan(1);
        assertThat(primeiraPagina.getContent()).hasSize(2);
        assertThat(segundaPagina.getContent()).hasSize(2);
    }

    @Test
    void paginaContasDoCorrentistaNoBanco() {
        Correntista correntista = correntistaRepository.save(novoCorrentista("pagination-account-user"));
        for (int indice = 0; indice < 5; indice++) {
            Conta conta = new Conta();
            conta.setNumero("PAG-" + indice);
            conta.setDescricao("Conta de paginação " + indice);
            conta.setTipo(TipoConta.CORRENTE);
            conta.setCorrentista(correntista);
            contaRepository.save(conta);
        }

        Page<Conta> pagina = contaRepository.findByCorrentistaId(correntista.getId(),
                PageRequest.of(1, 2, Sort.by("id").ascending()));

        assertThat(pagina.getTotalPages()).isGreaterThan(1);
        assertThat(pagina.getContent()).hasSize(2);
    }

    private Correntista novoCorrentista(String login) {
        Correntista correntista = new Correntista();
        correntista.setNome("Usuário de paginação");
        correntista.setLogin(login);
        correntista.setSenha("123");
        correntista.setPapel(Papel.CORRENTISTA);
        correntista.setBloqueado(false);
        return correntista;
    }
}