package backend.controller;

import backend.dto.CorrentistaForm;
import org.springframework.ui.Model;
import backend.service.CorrentistaService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/correntistas")
public class CorrentistaController {

    private final CorrentistaService correntistaService;

    public CorrentistaController(CorrentistaService correntistaService) {
        this.correntistaService = correntistaService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("correntistas", correntistaService.listarTodos());
        return "correntista/list";
    }

    @GetMapping("/nova")
    public String form(Model model) {
        model.addAttribute("correntistaForm", new CorrentistaForm());
        return "correntista/form";
    }

    @PostMapping
    public String criar(
            @ModelAttribute("correntistaForm") CorrentistaForm form, Model model,
            RedirectAttributes redirectAttributes) {

        try {
            correntistaService.criar(form);
        } catch (IllegalArgumentException e) {
            model.addAttribute("erro", e.getMessage());
            return "correntista/form";
        }

        redirectAttributes.addFlashAttribute(
                "sucesso",
                "Correntista cadastrado com sucesso."
        );

        return "redirect:/correntistas";
    }

}
