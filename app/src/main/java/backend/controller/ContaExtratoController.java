package backend.controller;

import backend.model.Comentario;
import backend.model.Conta;
import backend.model.Correntista;
import backend.model.Papel;
import backend.model.Transacao;
import backend.repository.ComentarioRepository;
import backend.repository.ContaRepository;
import backend.repository.TransacaoRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Controller
@RequestMapping("/contas")
public class ContaExtratoController {

    private final ContaRepository contaRepository;
    private final TransacaoRepository transacaoRepository;
    private final ComentarioRepository comentarioRepository;

    public ContaExtratoController(ContaRepository contaRepository,
            TransacaoRepository transacaoRepository,
            ComentarioRepository comentarioRepository) {
        this.contaRepository = contaRepository;
        this.transacaoRepository = transacaoRepository;
        this.comentarioRepository = comentarioRepository;
    }

    @GetMapping
    public String listarContas(Model model, HttpSession session) {
        Correntista usuario = usuarioDaSessao(session);
        var contas = usuario.getPapel() == Papel.ADMINISTRADOR
                ? contaRepository.findAll()
                : contaRepository.findByCorrentistaId(usuario.getId());
        model.addAttribute("contas", contas);
        return "conta/list";
    }

    @GetMapping("/{id}/extrato")
    public String extrato(@PathVariable Long id,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate dataInicial,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate dataFinal,
            HttpSession session, Model model) {
        Conta conta = contaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Conta não encontrada."));
        validarAcesso(conta, session);

        if (dataInicial == null && dataFinal == null) {
            YearMonth mesAtual = YearMonth.now();
            dataInicial = mesAtual.atDay(1);
            dataFinal = mesAtual.atEndOfMonth();
        }

        model.addAttribute("conta", conta);
        model.addAttribute("dataInicial", dataInicial);
        model.addAttribute("dataFinal", dataFinal);

        if (dataInicial == null || dataFinal == null || dataInicial.isAfter(dataFinal)) {
            model.addAttribute("erro", "Informe uma data inicial e uma data final válidas.");
            model.addAttribute("transacoes", Collections.emptyList());
            model.addAttribute("comentarios", Collections.emptyMap());
            return "conta/extrato";
        }

        var transacoes = transacaoRepository
                .findByContaIdAndDataBetweenOrderByDataAscIdAsc(id, dataInicial, dataFinal);
        var ids = transacoes.stream().map(Transacao::getId).toList();
        Map<Long, Comentario> comentarios = ids.isEmpty()
                ? Collections.emptyMap()
                : comentarioRepository.findByTransacaoIdIn(ids).stream()
                        .collect(Collectors.toMap(c -> c.getTransacao().getId(), Function.identity()));

        model.addAttribute("transacoes", transacoes);
        model.addAttribute("comentarios", comentarios);
        return "conta/extrato";
    }

    private Correntista usuarioDaSessao(HttpSession session) {
        return (Correntista) session.getAttribute("usuario");
    }

    private void validarAcesso(Conta conta, HttpSession session) {
        Correntista usuario = usuarioDaSessao(session);
        if (usuario.getPapel() != Papel.ADMINISTRADOR
                && !conta.getCorrentista().getId().equals(usuario.getId())) {
            throw new ResponseStatusException(FORBIDDEN, "Acesso negado.");
        }
    }
}