package fr.ut1.miage.service.impl;

import fr.ut1.miage.exception.JpaException;
import fr.ut1.miage.model.Livrer;
import fr.ut1.miage.repository.LivrerRepository;
import fr.ut1.miage.service.LivrerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LivrerServiceImpl implements LivrerService {

    private final LivrerRepository livrerRepository;

    @Override
    public void create(Livrer livrer) {
        try {
            livrerRepository.save(livrer);
        } catch (JpaException ex) {
            throw new JpaException("Failed to create ", ex);
        }
    }

}
