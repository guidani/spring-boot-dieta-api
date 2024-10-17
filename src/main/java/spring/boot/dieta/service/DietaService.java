package spring.boot.dieta.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.boot.dieta.model.Dieta;
import spring.boot.dieta.repository.DietaRepository;

@Service
public class DietaService {

  @Autowired
  private DietaRepository repository;

  public Dieta adicionarDieta(Dieta dieta) {
    dieta.getAlimentos().forEach(alimento -> alimento.setDieta(dieta));
    return repository.save(dieta);
  }

  public List<Dieta> listarDietas() {
    return repository.findAll();
  }

  public List<Dieta> listarDietasPorDia(LocalDate dia) {
    return repository.findByDia(dia);
  }

  public Optional<Dieta> buscarDietaPorId(Long id) {
    return repository.findById(id);
  }
  
}
