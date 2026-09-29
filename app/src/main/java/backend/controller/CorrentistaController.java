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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;


@Controller
@RequestMapping("/correntistas")
public class CorrentistaController {

    private final CorrentistaService correntistaService;

    public CorrentistaController(CorrentistaService correntistaService) {
        this.correntistaService = correntistaService;
    }

    @GetMapping
    public String listar(@PageableDefault(size = 9, sort = "id") Pageable pageable, Model model) {
        Pageable paginaLimitada = limitarPagina(pageable);
        model.addAttribute("correntistas", correntistaService.listarTodos(paginaLimitada));
        return "correntista/list";
    }

    private Pageable limitarPagina(Pageable pageable) {
        return PageRequest.of(pageable.getPageNumber(), Math.min(pageable.getPageSize(), 50),
                Sort.by(Sort.Direction.ASC, "id"));
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
