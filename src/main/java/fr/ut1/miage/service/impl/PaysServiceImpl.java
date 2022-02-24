package fr.ut1.miage.service.impl;

import fr.ut1.miage.exception.JpaException;
import fr.ut1.miage.model.Pays;
import fr.ut1.miage.repository.PaysRepository;
import fr.ut1.miage.service.PaysService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaysServiceImpl implements PaysService {

    private final PaysRepository paysRepository;

    @Override
    public void create(Pays pays) {
        try {
            paysRepository.save(pays);
        } catch (JpaException ex) {
            throw new JpaException("Failed to create ", ex);
        }
    }

}
