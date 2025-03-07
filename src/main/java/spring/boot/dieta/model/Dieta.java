package spring.boot.dieta.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dieta extends RepresentationModel<Dieta> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private LocalDate dia;
  private LocalTime horario;

  @OneToMany(mappedBy = "dieta", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Alimento> alimentos;
}
