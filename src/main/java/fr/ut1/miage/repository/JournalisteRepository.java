package fr.ut1.miage.repository;

import fr.ut1.miage.model.Journaliste;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JournalisteRepository extends JpaRepository<Journaliste, Integer> {
}
