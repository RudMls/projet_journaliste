package fr.ut1.miage.repository;

import fr.ut1.miage.model.DiffuserPU;
import fr.ut1.miage.model.embeddable.DiffuserPUId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiffuserPURepository extends JpaRepository<DiffuserPU, DiffuserPUId> {
}
