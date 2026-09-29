package backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.model.Conta;
import org.springframework.stereotype.Repository;
import backend.model.Correntista;

import java.util.List;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {
    List<Conta> findByCorrentista(Correntista correntista); // não pod ser finAll pq traz todas as contas da tabela, de todos os correntistas, finBy traz só do usu´rio
}
