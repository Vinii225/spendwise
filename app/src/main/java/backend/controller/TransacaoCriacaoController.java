package backend.controller;

import backend.dto.TransacaoForm;
import backend.model.Conta;
import backend.model.Correntista;
import backend.model.Movimento;
import backend.model.Papel;
import backend.repository.CategoriaRepository;
import backend.repository.ContaRepository;
import backend.service.TransacaoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Controller
@RequestMapping("/transacoes")
public class TransacaoCriacaoController {

    private final TransacaoService transacaoService;
    private final ContaRepository contaRepository;
    private final CategoriaRepository categoriaRepository;

    public TransacaoCriacaoController(TransacaoService transacaoService,
            ContaRepository contaRepository, CategoriaRepository categoriaRepository) {
        this.transacaoService = transacaoService;
        this.contaRepository = contaRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @GetMapping("/nova")
    public String formulario(@RequestParam Long contaId, HttpSession session, Model model) {
        Conta conta = buscarConta(contaId);
        validarAcesso(conta, session);
        prepararFormulario(model, conta);
        return "transacao/nova";
    }

    @PostMapping
    public String criar(@RequestParam Long contaId,
            @ModelAttribute("transacaoForm") TransacaoForm form,
            HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        Conta conta = buscarConta(contaId);
        validarAcesso(conta, session);
        try {
            transacaoService.criar(conta, form);
            redirectAttributes.addFlashAttribute("sucesso", "Transação criada com sucesso.");
            return "redirect:/contas/" + contaId + "/extrato";
        } catch (IllegalArgumentException e) {
            model.addAttribute("erro", e.getMessage());
            prepararFormulario(model, conta);
            return "transacao/nova";
        }
    }

    private void prepararFormulario(Model model, Conta conta) {
        model.addAttribute("conta", conta);
        model.addAttribute("transacaoForm", new TransacaoForm());
        model.addAttribute("categorias", categoriaRepository.findAll());
        model.addAttribute("movimentos", Movimento.values());
    }

    private Conta buscarConta(Long id) {
        return contaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Conta não encontrada."));
    }

    private void validarAcesso(Conta conta, HttpSession session) {
        Correntista usuario = (Correntista) session.getAttribute("usuario");
        if (usuario.isBloqueado()
                || (usuario.getPapel() != Papel.ADMINISTRADOR
                && !conta.getCorrentista().getId().equals(usuario.getId()))) {
            throw new ResponseStatusException(FORBIDDEN, "Acesso negado.");
        }
    }
}