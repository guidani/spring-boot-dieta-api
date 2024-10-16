package spring.boot.dieta.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import spring.boot.dieta.model.Dieta;
import spring.boot.dieta.service.DietaService;

@RestController
@RequestMapping("/api/dietas")
public class DietaController {

  @Autowired
  private DietaService dietaService;

  @PostMapping("/adicionar")
  public ResponseEntity<Dieta> criarDieta(@RequestBody Dieta dieta) {
    Dieta novaDieta = dietaService.adicionarDieta(dieta);
    return ResponseEntity.ok(novaDieta);
  }

  @GetMapping("/listar")
  public ResponseEntity<List<Dieta>> listarDietas() {
    List<Dieta> dietas = dietaService.listarDietas();
    return ResponseEntity.ok(dietas);
  }

  @GetMapping("/por-dia/{dia}")
  public ResponseEntity<List<Dieta>> obterDietasPorDia(
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dia) {
    List<Dieta> dietas = dietaService.listarDietasPorDia(dia);
    return ResponseEntity.ok(dietas);
  }

}
