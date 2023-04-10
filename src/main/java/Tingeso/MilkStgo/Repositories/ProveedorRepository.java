package Tingeso.MilkStgo.Repositories;

import Tingeso.MilkStgo.Entities.ProveedorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorRepository extends JpaRepository<ProveedorEntity, Long> {
    @Query("select e from ProveedorEntity e where e.id_proveedor = :id_proveedor")
    ProveedorEntity findByIdProveedor(@Param("id_proveedor") Long id_proveedor);
    @Query("select e from ProveedorEntity e where e.categoria = :categoria")
    ProveedorEntity findByCategoria(@Param("categoria") String categoria);
    @Query("select e from ProveedorEntity e where e.retencion = :retencion")
    ProveedorEntity findByRetencion(@Param("retencion") String retencion);

}
