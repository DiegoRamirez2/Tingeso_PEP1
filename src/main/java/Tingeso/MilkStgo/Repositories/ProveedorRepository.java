package Tingeso.MilkStgo.Repositories;

import Tingeso.MilkStgo.Entities.ProveedorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorRepository extends JpaRepository<ProveedorEntity, Long> {
    @Query("select p from ProveedorEntity p where p.id_proveedor = :id_proveedor")
    ProveedorEntity findByIdProveedor(@Param("id_proveedor") Long id_proveedor);
    @Query("select p from ProveedorEntity p where p.categoria = :categoria")
    ProveedorEntity findByCategoria(@Param("categoria") String categoria);
    @Query("select p from ProveedorEntity p where p.retencion = :retencion")
    ProveedorEntity findByRetencion(@Param("retencion") String retencion);
}
