package tingeso.milkstgo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pagos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagosEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago", unique = true, nullable = false)
    private Long idPago;
    private String idProveedor;
    private String nombreProveedor;
    private Integer anio;
    private Integer mes;
    private Integer quincena;
    private Integer klsLeche;
    private Integer nroDiasEnvios;
    private Double promDiarioKlsLeche;
    private Double varLeche;
    private Integer grasa;
    private Double varGrasa;
    private Integer solidosTotales;
    private Double varSolidosTotales;
    private Integer pagoLeche;
    private Integer pagoGrasa;
    private Integer pagoSolidosTotales;
    private Double bonificacionPorFrecuencia;
    private Double dctoVarLeche;
    private Double dctoVarGrasa;
    private Double dctoVarSolidosTotales;
    private Double pagoTotal;
    private Double montoRetencion;
    private Double montoFinal;
}
