package backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.servlet.http.HttpSession;
import backend.model.Correntista;

import backend.dto.ContaForm;
import backend.model.TipoConta;
import backend.repository.CorrentistaRepository;
import backend.service.ContaService;

@Controller
@RequestMapping("/contas")
public class ContaController {

    private final ContaService contaService;
    private final CorrentistaRepository correntistaRepository;

    public ContaController(ContaService contaService, CorrentistaRepository correntistaRepository) {
        this.contaService = contaService;
        this.correntistaRepository = correntistaRepository;
    }

    @GetMapping("/nova")
    public String form(Model model) {
        model.addAttribute("contaForm", new ContaForm());
        adicionarListas(model);
        return "conta/form";
    }

    @PostMapping
    public String criar(@ModelAttribute("contaForm") ContaForm form, Model model,
            RedirectAttributes redirectAttributes) {
        try {
            contaService.criar(form);
            redirectAttributes.addFlashAttribute("sucesso", "Conta criada com sucesso.");
            return "redirect:/contas/nova";
        } catch (IllegalArgumentException e) {
            model.addAttribute("erro", e.getMessage());
            adicionarListas(model);
            return "conta/form";
        }
    }

    private void adicionarListas(Model model) {
        model.addAttribute("correntistas", correntistaRepository.findAll());
        model.addAttribute("tipos", TipoConta.values());
    }

    @GetMapping
    public String listar(HttpSession session, Model model) {

        Correntista correntista =
                (Correntista) session.getAttribute("usuario");

        model.addAttribute(
                "contas",
                contaService.listarPorCorrentista(correntista)
        );

        return "conta/list";
    }

}
