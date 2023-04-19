package tingeso.milkstgo.repositories;

import tingeso.milkstgo.entities.LaboratorioEntity;
import tingeso.milkstgo.entities.ProveedorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LaboratorioRepository extends JpaRepository<LaboratorioEntity, Long> {
    @Query("select l from LaboratorioEntity l where l.proveedor.idProveedor = :id_proveedor")
    LaboratorioEntity findByProveedorId(@Param("id_proveedor") Long idProveedor);

    @Query("select l.proveedor from LaboratorioEntity l where l.proveedor.idProveedor = :id_proveedor")
    ProveedorEntity findRetencionByProveedorId(@Param("id_proveedor") Long idProveedor);
}
