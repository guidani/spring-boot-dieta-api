package spring.boot.dieta.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    if (dietas.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    for (Dieta dieta : dietas) {
      long id = dieta.getId();
      dieta.add(linkTo(methodOn(DietaController.class).obterDietaPorId(id)).withSelfRel());
    }
    return new ResponseEntity<List<Dieta>>(dietas, HttpStatus.OK);
  }

  @GetMapping("/por-dia/{dia}")
  public ResponseEntity<List<Dieta>> obterDietasPorDia(
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dia) {
    List<Dieta> dietas = dietaService.listarDietasPorDia(dia);
    return new ResponseEntity<List<Dieta>>(dietas, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Dieta> obterDietaPorId(@PathVariable Long id) {
    Optional<Dieta> optionalDieta = dietaService.buscarDietaPorId(id);
    if (optionalDieta.isPresent()) {
      Dieta dieta = optionalDieta.get();
      dieta.add(linkTo(methodOn(DietaController.class).listarDietas()).withRel("Listar dietas"));
      return new ResponseEntity<Dieta>(dieta, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}