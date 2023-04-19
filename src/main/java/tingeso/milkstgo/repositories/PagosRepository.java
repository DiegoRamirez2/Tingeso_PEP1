package tingeso.milkstgo.repositories;

import tingeso.milkstgo.entities.PagosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PagosRepository extends JpaRepository<PagosEntity, Long> {
    @Query("select p from PagosEntity p where " +
            "p.idProveedor = :id_proveedor " +
            "and p.anio = :anio " +
            "and p.mes = :mes " +
            "and p.quincena = :quincena")
    PagosEntity findPagosByQuincena(@Param("id_proveedor") String idProveedor,
                                    @Param("anio") Integer anio,
                                    @Param("mes") Integer mes,
                                    @Param("quincena") Integer quincena);

}
