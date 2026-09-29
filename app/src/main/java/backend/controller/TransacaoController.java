package backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import backend.dto.TransacaoForm;
import backend.model.Correntista;
import backend.model.Movimento;
import backend.model.Papel;
import backend.model.Transacao;
import backend.repository.CategoriaRepository;
import backend.service.TransacaoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.FORBIDDEN;

@Controller
@RequestMapping("/transacoes")
public class TransacaoController {

    private final TransacaoService transacaoService;
    private final CategoriaRepository categoriaRepository;

    public TransacaoController(TransacaoService transacaoService,
            CategoriaRepository categoriaRepository) {
        this.transacaoService = transacaoService;
        this.categoriaRepository = categoriaRepository;
    }

    @GetMapping("/{id}/editar")
    public String formularioEdicao(@PathVariable Long id, HttpSession session, Model model) {
        Transacao transacao = transacaoService.buscarPorId(id);
        validarAcesso(transacao, session);
        TransacaoForm form = new TransacaoForm();
        form.setData(transacao.getData());
        form.setDescricao(transacao.getDescricao());
        form.setValor(transacao.getValor());
        form.setMovimento(transacao.getMovimento());
        form.setCategoriaId(transacao.getCategoria().getId());

        model.addAttribute("transacaoForm", form);
        model.addAttribute("transacao", transacao);
        adicionarListas(model);
        return "transacao/form";
    }

    @PostMapping("/{id}")
    public String editar(@PathVariable Long id,
            @ModelAttribute("transacaoForm") TransacaoForm form,
            HttpSession session, Model model,
            RedirectAttributes redirectAttributes) {
        try {
            validarAcesso(transacaoService.buscarPorId(id), session);
            transacaoService.editar(id, form);
            redirectAttributes.addFlashAttribute("sucesso", "Transação editada com sucesso.");
            return "redirect:/transacoes/" + id + "/editar";
        } catch (IllegalArgumentException e) {
            model.addAttribute("erro", e.getMessage());
            model.addAttribute("transacao", transacaoService.buscarPorId(id));
            adicionarListas(model);
            return "transacao/form";
        }
    }

    private void adicionarListas(Model model) {
        model.addAttribute("categorias", categoriaRepository.findAll());
        model.addAttribute("movimentos", Movimento.values());
    }

    private void validarAcesso(Transacao transacao, HttpSession session) {
        Correntista usuario = (Correntista) session.getAttribute("usuario");
        if (usuario.isBloqueado()
                || (usuario.getPapel() != Papel.ADMINISTRADOR
                && !transacao.getConta().getCorrentista().getId().equals(usuario.getId()))) {
            throw new ResponseStatusException(FORBIDDEN, "Acesso negado.");
        }
    }
}