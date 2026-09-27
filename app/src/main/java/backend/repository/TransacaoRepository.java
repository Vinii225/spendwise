package backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.model.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
}
