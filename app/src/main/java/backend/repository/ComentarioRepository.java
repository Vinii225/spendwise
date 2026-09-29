package backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.model.Comentario;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
	List<Comentario> findByTransacaoIdIn(Collection<Long> transacaoIds);
}
