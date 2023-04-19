package tingeso.milkstgo.repositories;

import tingeso.milkstgo.entities.ProveedorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorRepository extends JpaRepository<ProveedorEntity, Long> {
    @Query("select p from ProveedorEntity p where p.idProveedor = :id_proveedor")
    ProveedorEntity findByIdProveedor(@Param("id_proveedor") Long idProveedor);
}
