package Tingeso.MilkStgo.Entities;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
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
    private Long id_pago;
    private String id_proveedor;
    private String nombre_proveedor;
    private Integer anio;
    private Integer mes;
    private Integer quincena;
    private Integer kls_leche;
    private Integer nro_dias_envios;
    private Double prom_diario_kls_leche;
    private Double var_leche;
    private Integer grasa;
    private Double var_grasa;
    private Integer solidos_totales;
    private Double var_solidos_totales;
    private Integer pago_leche;
    private Integer pago_grasa;
    private Integer pago_solidos_totales;
    private Double bonificacion_por_frecuencia;
    private Double dcto_var_leche;
    private Double dcto_var_grasa;
    private Double dcto_var_solidos_totales;
    private Double pago_total;
    private Double monto_retencion;
    private Double monto_final;
}
