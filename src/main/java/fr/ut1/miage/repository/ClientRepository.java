package fr.ut1.miage.repository;

import fr.ut1.miage.model.Client;
import fr.ut1.miage.model.Ville;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {

    boolean existsByNomAndPrenom(String nom, String prenom);

}
