package Tingeso.MilkStgo.Repositories;

import Tingeso.MilkStgo.Entities.PagosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagosRepository extends JpaRepository<PagosEntity, Long> {
}
