package Tingeso.MilkStgo.Repositories;

import Tingeso.MilkStgo.Entities.PagosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface PagosRepository extends JpaRepository<PagosEntity, Long> {
    @Query("select p from PagosEntity p where " +
            "p.id_proveedor = :id_proveedor " +
            "and p.anio = :anio " +
            "and p.mes = :mes " +
            "and p.quincena = :quincena")
    PagosEntity findPagosByQuincena(@Param("id_proveedor") String id_proveedor,
                                    @Param("anio") Integer anio,
                                    @Param("mes") Integer mes,
                                    @Param("quincena") Integer quincena);
}
