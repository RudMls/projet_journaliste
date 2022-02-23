package fr.ut1.miage.repository;

import fr.ut1.miage.model.Pays;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaysRepository extends JpaRepository<Pays, Integer> {
}
