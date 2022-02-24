package fr.ut1.miage.service.impl;

import fr.ut1.miage.exception.JpaException;
import fr.ut1.miage.model.Ville;
import fr.ut1.miage.repository.VilleRepository;
import fr.ut1.miage.service.VilleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class VilleServiceImpl implements VilleService {

    private final VilleRepository villeRepository;

    @Override
    public void create(Ville ville) {
        try {
            villeRepository.save(ville);
        } catch (JpaException ex) {
            throw new JpaException("Failed to create ", ex);
        }
    }

    @Override
    public List<Ville> getAll() {
        try {
            return villeRepository.findAll();
        } catch (JpaException ex) {
            throw new JpaException("Failed to get all ", ex);
        }
    }

}
