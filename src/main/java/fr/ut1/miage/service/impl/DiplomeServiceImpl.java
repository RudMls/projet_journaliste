package fr.ut1.miage.service.impl;

import fr.ut1.miage.exception.JpaException;
import fr.ut1.miage.model.Diplome;
import fr.ut1.miage.repository.DiplomeRepository;
import fr.ut1.miage.service.DiplomeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DiplomeServiceImpl implements DiplomeService {

    private final DiplomeRepository diplomeRepository;

    @Override
    public void create(Diplome diplome) {
        try {
            diplomeRepository.save(diplome);
        } catch (JpaException ex) {
            throw new JpaException("Failed to create ", ex);
        }
    }

    @Override
    public List<Diplome> getAll() {
        try {
            return diplomeRepository.findAll();
        } catch (JpaException ex) {
            throw new JpaException("Failed to get all ", ex);
        }
    }

}
