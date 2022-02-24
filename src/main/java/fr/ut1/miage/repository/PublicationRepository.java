package fr.ut1.miage.repository;

import fr.ut1.miage.model.Publication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicationRepository extends JpaRepository<Publication, Integer> {
}
