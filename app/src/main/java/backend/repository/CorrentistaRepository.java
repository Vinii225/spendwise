package backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.model.Correntista;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CorrentistaRepository extends JpaRepository<Correntista, Long> {
    Optional<Correntista> findByLogin(String login);
}
