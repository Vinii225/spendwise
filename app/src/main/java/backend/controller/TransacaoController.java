package backend.controller;

import backend.dto.TransacaoForm;
import backend.model.Correntista;
import backend.repository.CategoriaRepository;
import backend.service.TransacaoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/contas/{contaId}/transacoes")
public class TransacaoController {

    private final TransacaoService transacaoService;
    private final CategoriaRepository categoriaRepository;

    public TransacaoController(TransacaoService transacaoService, CategoriaRepository categoriaRepository) {

        this.transacaoService = transacaoService;
        this.categoriaRepository = categoriaRepository;

    }

    @GetMapping("/nova")
    public String form( @PathVariable Long contaId,  Model model) {

        model.addAttribute("transacaoForm", new TransacaoForm()
        );

        model.addAttribute("categorias", categoriaRepository.findAll()
        );

        model.addAttribute("contaId", contaId
        );

        return "transacao/form";
    }

    @PostMapping
    public String criar(@PathVariable Long contaId, @ModelAttribute("transacaoForm") TransacaoForm form,
            HttpSession session) {

        Correntista correntista =
                (Correntista) session.getAttribute("usuario");

        transacaoService.criar(
                form,
                contaId,
                correntista
        );

        return "redirect:/contas";
    }

}
