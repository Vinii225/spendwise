package backend.controller;

import backend.repository.ComentarioRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/comentarios")
public class ComentarioConsultaController {

    private final ComentarioRepository comentarioRepository;

    public ComentarioConsultaController(ComentarioRepository comentarioRepository) {
        this.comentarioRepository = comentarioRepository;
    }

    @GetMapping("/{id}")
    public String visualizar(@PathVariable Long id, Model model) {
        model.addAttribute("comentario", comentarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Comentário não foi encontrado.")));
        return "comentario/view";
    }
}