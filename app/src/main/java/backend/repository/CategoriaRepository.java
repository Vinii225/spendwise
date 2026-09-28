package backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.model.Categoria;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
