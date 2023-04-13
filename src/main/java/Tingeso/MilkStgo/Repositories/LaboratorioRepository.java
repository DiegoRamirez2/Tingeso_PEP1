package Tingeso.MilkStgo.Repositories;

import Tingeso.MilkStgo.Entities.LaboratorioEntity;
import Tingeso.MilkStgo.Entities.ProveedorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LaboratorioRepository extends JpaRepository<LaboratorioEntity, Long> {
    @Query("select l from LaboratorioEntity l where l.proveedor.id_proveedor = :id_proveedor")
    LaboratorioEntity findByProveedorId(@Param("id_proveedor") Long id_proveedor);

    @Query("select l.proveedor from LaboratorioEntity l where l.proveedor.id_proveedor = :id_proveedor")
    ProveedorEntity findRetencionByProveedorId(@Param("id_proveedor") Long id_proveedor);
}
