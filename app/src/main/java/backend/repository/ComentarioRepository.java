package backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.model.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
}
