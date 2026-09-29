package backend.controller;

import backend.dto.ComentarioForm;
import backend.model.Comentario;
import backend.model.Correntista;
import backend.model.Papel;
import backend.model.Transacao;
import backend.repository.TransacaoRepository;
import backend.service.ComentarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class ComentarioCrudController {

    private final ComentarioService comentarioService;
    private final TransacaoRepository transacaoRepository;

    public ComentarioCrudController(ComentarioService comentarioService,
            TransacaoRepository transacaoRepository) {
        this.comentarioService = comentarioService;
        this.transacaoRepository = transacaoRepository;
    }

    @GetMapping("/transacoes/{id}/comentario")
    public String formularioCriacao(@PathVariable Long id, HttpSession session, Model model) {
        Transacao transacao = buscarTransacao(id);
        validarAcesso(transacao, session);
        model.addAttribute("transacao", transacao);
        model.addAttribute("comentarioForm", new ComentarioForm());
        return "comentario/form";
    }

    @PostMapping("/transacoes/{id}/comentario")
    public String criar(@PathVariable Long id, @ModelAttribute("comentarioForm") ComentarioForm form,
            HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        Transacao transacao = buscarTransacao(id);
        validarAcesso(transacao, session);
        try {
            comentarioService.criar(transacao, form);
            redirectAttributes.addFlashAttribute("sucesso", "Comentário adicionado com sucesso.");
            return "redirect:/contas/" + transacao.getConta().getId() + "/extrato";
        } catch (IllegalArgumentException e) {
            model.addAttribute("erro", e.getMessage());
            model.addAttribute("transacao", transacao);
            return "comentario/form";
        }
    }

    @GetMapping("/comentarios/{id}/editar")
    public String formularioEdicao(@PathVariable Long id, HttpSession session, Model model) {
        Comentario comentario = comentarioService.buscarPorId(id);
        validarAcesso(comentario.getTransacao(), session);
        ComentarioForm form = new ComentarioForm();
        form.setTexto(comentario.getTexto());
        model.addAttribute("comentario", comentario);
        model.addAttribute("transacao", comentario.getTransacao());
        model.addAttribute("comentarioForm", form);
        return "comentario/form";
    }

    @PostMapping("/comentarios/{id}")
    public String editar(@PathVariable Long id, @ModelAttribute("comentarioForm") ComentarioForm form,
            HttpSession session, RedirectAttributes redirectAttributes) {
        Comentario comentario = comentarioService.buscarPorId(id);
        validarAcesso(comentario.getTransacao(), session);
        comentarioService.editar(id, form);
        redirectAttributes.addFlashAttribute("sucesso", "Comentário atualizado com sucesso.");
        return "redirect:/contas/" + comentario.getTransacao().getConta().getId() + "/extrato";
    }

    @PostMapping("/comentarios/{id}/excluir")
    public String excluir(@PathVariable Long id, HttpSession session, RedirectAttributes redirectAttributes) {
        Comentario comentario = comentarioService.buscarPorId(id);
        validarAcesso(comentario.getTransacao(), session);
        Long contaId = comentario.getTransacao().getConta().getId();
        comentarioService.excluir(id);
        redirectAttributes.addFlashAttribute("sucesso", "Comentário excluído com sucesso.");
        return "redirect:/contas/" + contaId + "/extrato";
    }

    private Transacao buscarTransacao(Long id) {
        return transacaoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Transação não encontrada."));
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