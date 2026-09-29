package backend.service;

import backend.dto.ContaForm;
import backend.model.Conta;
import backend.model.Correntista;
import backend.model.TipoConta;
import backend.repository.ContaRepository;
import backend.repository.CorrentistaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ContaServiceTest {

    @Mock
    private ContaRepository contaRepository;

    @Mock
    private CorrentistaRepository correntistaRepository;

    private ContaService contaService;

    @BeforeEach
    void setUp() {
        contaService = new ContaService(contaRepository, correntistaRepository);
    }

    private ContaForm formValido(TipoConta tipo, Integer diaFechamento) {
        ContaForm form = new ContaForm();
        form.setCorrentistaId(1L);
        form.setNumero("0001-1");
        form.setDescricao("Conta de teste");
        form.setTipo(tipo);
        form.setDiaFechamento(diaFechamento);
        return form;
    }

    @Test
    void deveCriarContaCorrenteSemDiaFechamento() {
        Correntista correntista = new Correntista();
        correntista.setId(1L);
        when(correntistaRepository.findById(1L)).thenReturn(Optional.of(correntista));
        when(contaRepository.save(any(Conta.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Conta conta = contaService.criar(formValido(TipoConta.CORRENTE, null));

        assertThat(conta.getTipo()).isEqualTo(TipoConta.CORRENTE);
        assertThat(conta.getDiaFechamento()).isNull();
        assertThat(conta.getCorrentista()).isEqualTo(correntista);
    }

    @Test
    void deveCriarContaCartaoComDiaFechamento() {
        Correntista correntista = new Correntista();
        correntista.setId(1L);
        when(correntistaRepository.findById(1L)).thenReturn(Optional.of(correntista));
        when(contaRepository.save(any(Conta.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Conta conta = contaService.criar(formValido(TipoConta.CARTAO, 10));

        assertThat(conta.getTipo()).isEqualTo(TipoConta.CARTAO);
        assertThat(conta.getDiaFechamento()).isEqualTo(10);
    }

    @Test
    void naoDeveCriarContaCartaoSemDiaFechamento() {
        assertThatThrownBy(() -> contaService.criar(formValido(TipoConta.CARTAO, null)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Dia para fechamento");

        verify(contaRepository, never()).save(any());
        verify(correntistaRepository, never()).findById(any());
    }

    @Test
    void naoDeveCriarContaCorrenteComDiaFechamento() {
        assertThatThrownBy(() -> contaService.criar(formValido(TipoConta.CORRENTE, 10)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("só é necessário para contas de tipo cartão");

        verify(contaRepository, never()).save(any());
        verify(correntistaRepository, never()).findById(any());
    }

    @Test
    void naoDeveCriarContaParaCorrentistaInexistente() {
        when(correntistaRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> contaService.criar(formValido(TipoConta.CORRENTE, null)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Correntista não foi encontrado");

        verify(contaRepository, never()).save(any());
    }

    @Test
    void deveSalvarContaComOsDadosDoFormulario() {
        Correntista correntista = new Correntista();
        correntista.setId(1L);
        when(correntistaRepository.findById(1L)).thenReturn(Optional.of(correntista));
        when(contaRepository.save(any(Conta.class))).thenAnswer(invocation -> invocation.getArgument(0));

        contaService.criar(formValido(TipoConta.CORRENTE, null));

        ArgumentCaptor<Conta> captor = ArgumentCaptor.forClass(Conta.class);
        verify(contaRepository).save(captor.capture());

        Conta salva = captor.getValue();
        assertThat(salva.getNumero()).isEqualTo("0001-1");
        assertThat(salva.getDescricao()).isEqualTo("Conta de teste");
        assertThat(salva.getCorrentista()).isEqualTo(correntista);
    }
}
