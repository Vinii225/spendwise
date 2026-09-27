package backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.model.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long> {
}
