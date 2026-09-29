package backend.controller;

import backend.config.SecurityConfig;
import backend.dto.ContaForm;
import backend.model.Conta;
import backend.model.Correntista;
import backend.model.Papel;
import backend.model.TipoConta;
import backend.repository.CorrentistaRepository;
import backend.service.ContaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(ContaController.class)
@Import(SecurityConfig.class)
class ContaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ContaService contaService;

    @MockitoBean
    private CorrentistaRepository correntistaRepository;

    private Correntista usuarioLogado() {
        Correntista correntista = new Correntista();
        correntista.setId(1L);
        correntista.setNome("Fulano");
        correntista.setPapel(Papel.CORRENTISTA);
        return correntista;
    }

    @Test
    void deveExibirFormularioDeCriacaoDeConta() throws Exception {
        given(correntistaRepository.findAll()).willReturn(List.of(usuarioLogado()));

        mockMvc.perform(get("/contas/nova")
                        .sessionAttr("usuario", usuarioLogado()))
                .andExpect(status().isOk())
                .andExpect(view().name("conta/form"))
                .andExpect(model().attributeExists("contaForm", "correntistas", "tipos"));
    }

    @Test
    void semSessaoDeveRedirecionarParaLogin() throws Exception {
        mockMvc.perform(get("/contas/nova"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/auth"));
    }

    @Test
    void deveCriarContaERedirecionarComSucesso() throws Exception {
        given(contaService.criar(any(ContaForm.class))).willReturn(new Conta());

        mockMvc.perform(post("/contas")
                        .sessionAttr("usuario", usuarioLogado())
                        .param("correntistaId", "1")
                        .param("numero", "0001-1")
                        .param("descricao", "Conta de teste")
                        .param("tipo", TipoConta.CORRENTE.name()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/contas/nova"))
                .andExpect(flash().attributeExists("sucesso"));

        verify(contaService).criar(any(ContaForm.class));
    }

    @Test
    void deveVoltarAoFormularioComErroQuandoServiceRejeitar() throws Exception {
        given(correntistaRepository.findAll()).willReturn(List.of(usuarioLogado()));
        given(contaService.criar(any(ContaForm.class)))
                .willThrow(new IllegalArgumentException("Dia para fechamento é necessário para contas de tipo cartão."));

        mockMvc.perform(post("/contas")
                        .sessionAttr("usuario", usuarioLogado())
                        .param("correntistaId", "1")
                        .param("numero", "0001-1")
                        .param("descricao", "Conta de teste")
                        .param("tipo", TipoConta.CARTAO.name()))
                .andExpect(status().isOk())
                .andExpect(view().name("conta/form"))
                .andExpect(model().attribute("erro", "Dia para fechamento é necessário para contas de tipo cartão."))
                .andExpect(model().attributeExists("correntistas", "tipos"));
    }
}
