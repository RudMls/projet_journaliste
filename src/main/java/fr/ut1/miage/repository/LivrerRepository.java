package fr.ut1.miage.repository;

import fr.ut1.miage.model.Livrer;
import fr.ut1.miage.model.embeddable.LivrerId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivrerRepository extends JpaRepository<Livrer, LivrerId> {
}
