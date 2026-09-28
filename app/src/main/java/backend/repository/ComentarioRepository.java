package backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.model.Comentario;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
}
