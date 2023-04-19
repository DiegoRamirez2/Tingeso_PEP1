package tingeso.milkstgo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Entity
@Table(name = "acopio")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AcopioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_acopio", unique = true, nullable = false)
    private Long idAcopio;
    private String proveedor;
    private LocalDate fecha;
    private String turno;
    private Integer klsLeche;
}
