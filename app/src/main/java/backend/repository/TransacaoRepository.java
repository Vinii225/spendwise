package backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.model.Transacao;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
	List<Transacao> findByContaIdAndDataBetweenOrderByDataAscIdAsc(
			Long contaId, LocalDate dataInicial, LocalDate dataFinal);
}
