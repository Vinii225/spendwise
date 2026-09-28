package backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.model.Transacao;
import org.springframework.stereotype.Repository;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
}
