package backend.controller;

import backend.dto.CorrentistaForm;
import backend.service.CorrentistaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cadastro")
public class CadastroController {

    private final CorrentistaService correntistaService;

    public CadastroController(CorrentistaService correntistaService) {
        this.correntistaService = correntistaService;
    }

    @GetMapping
    public String formulario(Model model) {
        model.addAttribute("correntistaForm", new CorrentistaForm());
        return "auth/cadastro";
    }

    @PostMapping
    public String cadastrar(@ModelAttribute("correntistaForm") CorrentistaForm form, Model model) {
        try {
            correntistaService.criar(form);
            return "redirect:/auth?cadastro=sucesso";
        } catch (IllegalArgumentException e) {
            model.addAttribute("erro", e.getMessage());
            return "auth/cadastro";
        }
    }
}