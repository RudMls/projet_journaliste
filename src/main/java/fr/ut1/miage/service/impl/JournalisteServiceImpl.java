package fr.ut1.miage.service.impl;

import fr.ut1.miage.model.Journaliste;
import fr.ut1.miage.repository.JournalisteRepository;
import fr.ut1.miage.service.JournalisteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class JournalisteServiceImpl implements JournalisteService {

    private final JournalisteRepository journalisteRepository;

    @Override
    public void create(Journaliste journaliste) {
        try {
            journalisteRepository.save(journaliste);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
