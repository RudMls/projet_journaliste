package fr.ut1.miage.repository;

import fr.ut1.miage.model.Numero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NumeroRepository extends JpaRepository<Numero, Integer> {
}
