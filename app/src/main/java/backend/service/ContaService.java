package backend.service;

import org.springframework.stereotype.Service;
import backend.dto.ContaForm;
import backend.model.Conta;
import backend.model.Correntista;
import backend.model.TipoConta;
import backend.repository.ContaRepository;
import backend.repository.CorrentistaRepository;

@Service
public class ContaService {

    private final ContaRepository contaRepository;
    private final CorrentistaRepository correntistaRepository;

    public ContaService(ContaRepository contaRepository, CorrentistaRepository correntistaRepository) {
        this.contaRepository = contaRepository;
        this.correntistaRepository = correntistaRepository;
    }

    public Conta criar(ContaForm form) {
        validarDiaFechamento(form.getTipo(), form.getDiaFechamento());

        Correntista correntista = correntistaRepository.findById(form.getCorrentistaId())
            .orElseThrow(() -> new IllegalArgumentException("Correntista não foi encontrado."));
    
        Conta conta = new Conta();
        conta.setNumero(form.getNumero());
        conta.setDescricao(form.getDescricao());
        conta.setTipo(form.getTipo());
        conta.setDiaFechamento(form.getDiaFechamento());
        conta.setCorrentista(correntista);
    
        return contaRepository.save(conta);
    }

    // Info: diaFechamento é pra cartão, ñ aplica a corrente.
    private void validarDiaFechamento(TipoConta tipo, Integer diaFechamento) {
        if (tipo == TipoConta.CARTAO && diaFechamento == null) {
            throw new IllegalArgumentException("Dia para fechamento é necessário para contas de tipo cartão.");
        }
        
        if (tipo == TipoConta.CORRENTE && diaFechamento != null) {
            throw new IllegalArgumentException("Dia de fechamento só é necessário para contas de tipo cartão.");
        }
    }
}
