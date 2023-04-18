package Tingeso.MilkStgo.Repositories;

import Tingeso.MilkStgo.Entities.AcopioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;

@Repository
public interface AcopioRepository extends JpaRepository<AcopioEntity, Long> {
    @Query("select a from AcopioEntity a where a.fecha >= :fecha and a.turno = :turno and a.proveedor = :proveedor")
    ArrayList<AcopioEntity> findAcopioSinceFecha(@Param("fecha") LocalDate fecha, @Param("turno") String turno, @Param("proveedor") String proveedor);
    @Query("select count(distinct a.fecha) from AcopioEntity a where a.proveedor = :proveedor and a.fecha >= :fecha")
    Integer CountDaysAcopioByProveedor(@Param("proveedor") String proveedor, @Param("fecha") LocalDate fecha);
    @Query("SELECT SUM(a.kls_leche) FROM AcopioEntity a WHERE a.proveedor = :proveedor AND a.fecha >= :fecha")
    Integer LecheByProveedor(@Param("proveedor") String proveedor, @Param("fecha") LocalDate fecha);
    @Query("select distinct a.proveedor from AcopioEntity a")
    ArrayList<String> getProveedores();
    @Query("select a from AcopioEntity a where a.fecha = :fecha and a.turno = :turno and a.proveedor = :proveedor")
    AcopioEntity findAcopioByFechaTurnoProveedor(@Param("fecha") LocalDate fecha, @Param("turno") String turno, @Param("proveedor") String proveedor);

}
