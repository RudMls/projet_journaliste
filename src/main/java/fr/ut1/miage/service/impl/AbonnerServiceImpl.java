package fr.ut1.miage.service.impl;

import fr.ut1.miage.exception.JpaException;
import fr.ut1.miage.model.Abonner;
import fr.ut1.miage.repository.AbonnerRepository;
import fr.ut1.miage.service.AbonnerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AbonnerServiceImpl implements AbonnerService {

    private final AbonnerRepository abonnerRepository;

    @Override
    public void create(Abonner abonner) {
        try {
            abonnerRepository.save(abonner);
        } catch (JpaException ex) {
            throw new JpaException("Failed to create abonner", ex);
        }
    }

}
