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
    private Double porcentaje_grasa;
    private Double porcentaje_solidos_totales;
    @OneToOne
    @JoinColumn(name = "id_proveedor")
    private ProveedorEntity proveedor;
}
