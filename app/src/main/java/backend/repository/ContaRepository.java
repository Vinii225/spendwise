package backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.model.Conta;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {
	Page<Conta> findByCorrentistaId(Long correntistaId, Pageable pageable);
}
