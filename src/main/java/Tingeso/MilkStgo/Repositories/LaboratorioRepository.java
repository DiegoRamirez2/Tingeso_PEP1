package Tingeso.MilkStgo.Repositories;

import Tingeso.MilkStgo.Entities.LaboratorioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaboratorioRepository extends JpaRepository<LaboratorioEntity, Long> {
}
