package Tingeso.MilkStgo.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "proveedor")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProveedorEntity {
    @Id
    @Column(name = "id_proveedor", unique = true, nullable = false)
    private Long id_proveedor;
    private String nombre;
    private String categoria;
    private String retencion;

}
