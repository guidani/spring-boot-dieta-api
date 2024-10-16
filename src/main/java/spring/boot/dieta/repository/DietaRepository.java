package spring.boot.dieta.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spring.boot.dieta.model.Dieta;

@Repository
public interface DietaRepository extends JpaRepository<Dieta, Long> {

  List<Dieta> findByDia(LocalDate dia);

}
