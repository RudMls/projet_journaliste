package fr.ut1.miage.repository;

import fr.ut1.miage.model.Payer;
import fr.ut1.miage.model.embeddable.PayerId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayerRepository extends JpaRepository<Payer, PayerId> {
}
