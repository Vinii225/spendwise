package backend.controller;

import backend.model.Correntista;
import backend.service.CorrentistaService;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final CorrentistaService correntistaService;

    public AuthController(CorrentistaService correntistaService) {
        this.correntistaService = correntistaService;
    }

    @GetMapping
    public String login(Model model) {
        model.addAttribute("loginForm", new Correntista());

        return "auth/login";
    }

    @PostMapping
    public String autenticar(
            @ModelAttribute("loginForm") Correntista form,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        Correntista usuario = correntistaService.autenticar(
                form.getLogin(),
                form.getSenha()
        );

        if (usuario == null) {
            redirectAttributes.addFlashAttribute(
                    "erro",
                    "Login ou senha inválidos."
            );

            return "redirect:/auth";
        }

        session.setAttribute("usuario", usuario);

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {

        session.invalidate();

        return "redirect:/auth";
    }
}