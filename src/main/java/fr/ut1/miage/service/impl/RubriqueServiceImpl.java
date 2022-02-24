package fr.ut1.miage.service.impl;

import fr.ut1.miage.exception.JpaException;
import fr.ut1.miage.model.Rubrique;
import fr.ut1.miage.repository.RubriqueRepository;
import fr.ut1.miage.service.RubriqueService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RubriqueServiceImpl implements RubriqueService {

    private final RubriqueRepository rubriqueRepository;

    @Override
    public void create(Rubrique rubrique) {
        try {
            rubriqueRepository.save(rubrique);
        } catch (JpaException ex) {
            throw new JpaException("Failed to create Rubrique", ex);
        }
    }

    @Override
    public List<Rubrique> getAll() {
        try {
            return rubriqueRepository.findAll();
        } catch (JpaException ex) {
            throw new JpaException("Failed to get all Rubriques", ex);
        }
    }

}
