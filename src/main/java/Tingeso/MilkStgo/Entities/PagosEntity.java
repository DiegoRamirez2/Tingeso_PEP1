package Tingeso.MilkStgo.Entities;

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
    private static float MANANA_TARDE = 1.2F;
    private static float MANANA = 1.12F;
    private static float TARDE = 1.08F;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago", unique = true, nullable = false)
    private Long id_pago;
    private Integer anio;
    private Integer mes;
    private Integer quincena;
    private Integer codigo_proveedor;
    private String nombre_proveedor;
    private Integer kls_leche;
    private Integer nro_dias_envios;
    private float prom_diario_kls_leche;
    private float var_leche;
    private Integer grasa;
    private float var_grasa;
    private Integer solidos_totales;
    private float var_solidos_totales;
    private float pago_leche;
    private float pago_grasa;
    private float pago_solidos_totales;
    private float bonificacion_por_frecuencia;
    private float dcto_var_leche;
    private float dcto_var_grasa;
    private float dcto_var_solidos_totales;
    private float pago_total;
    private float monto_retencion;
    private float monto_final;

}
