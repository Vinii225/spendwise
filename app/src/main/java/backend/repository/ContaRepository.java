package backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.model.Conta;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {
	List<Conta> findByCorrentistaId(Long correntistaId);
}
