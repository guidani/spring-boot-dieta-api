package spring.boot.dieta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spring.boot.dieta.model.Alimento;

@Repository
public interface AlimentoRepository extends JpaRepository<Alimento, Long> {

}
