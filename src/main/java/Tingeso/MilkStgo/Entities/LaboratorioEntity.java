package Tingeso.MilkStgo.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "Laboratorio")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LaboratorioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_laboratorio", unique = true, nullable = false)
    private Long id_laboratorio;
    private float porcentaje_grasa;
    private float porcentaje_solidos_totales;
    private LocalDate fecha_estudio;

}
