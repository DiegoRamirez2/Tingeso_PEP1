package tingeso.milkstgo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Laboratorio")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LaboratorioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_laboratorio", unique = true, nullable = false)
    private Long idLaboratorio;
    private Double porcentajeGrasa;
    private Double porcentajeSolidosTotales;
    @OneToOne
    @JoinColumn(name = "id_proveedor")
    private ProveedorEntity proveedor;
}
