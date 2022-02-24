package fr.ut1.miage.service.impl;

import fr.ut1.miage.exception.JpaException;
import fr.ut1.miage.model.Journaliste;
import fr.ut1.miage.repository.JournalisteRepository;
import fr.ut1.miage.service.JournalisteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class JournalisteServiceImpl implements JournalisteService {

    private final JournalisteRepository journalisteRepository;

    @Override
    public void create(Journaliste journaliste) {
        try {
            journalisteRepository.save(journaliste);
        } catch (JpaException ex) {
            throw new JpaException("Failed to create ", ex);
        }
    }

    @Override
    public List<Journaliste> getAll() {
        try {
            return journalisteRepository.findAll();
        } catch (JpaException ex) {
            throw new JpaException("Failed to get all ", ex);
        }
    }

}
