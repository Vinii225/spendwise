package backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.model.Correntista;

public interface CorrentistaRepository extends JpaRepository<Correntista, Long> {
}
