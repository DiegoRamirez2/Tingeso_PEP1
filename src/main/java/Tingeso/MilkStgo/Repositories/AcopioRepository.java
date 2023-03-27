package Tingeso.MilkStgo.Repositories;

import Tingeso.MilkStgo.Entities.AcopioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcopioRepository extends JpaRepository<AcopioEntity, Long> {
}
