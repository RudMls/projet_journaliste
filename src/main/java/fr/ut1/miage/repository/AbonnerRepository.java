package fr.ut1.miage.repository;

import fr.ut1.miage.model.Abonner;
import fr.ut1.miage.model.embeddable.AbonnerId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbonnerRepository extends JpaRepository<Abonner, AbonnerId> {
}
