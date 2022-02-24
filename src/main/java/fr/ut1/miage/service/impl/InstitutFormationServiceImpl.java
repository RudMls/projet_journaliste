package fr.ut1.miage.service.impl;

import fr.ut1.miage.exception.JpaException;
import fr.ut1.miage.model.InstitutFormation;
import fr.ut1.miage.repository.InstitutFormationRepository;
import fr.ut1.miage.service.InstitutFormationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InstitutFormationServiceImpl implements InstitutFormationService {

    private final InstitutFormationRepository institutFormationRepository;

    @Override
    public void create(InstitutFormation institutFormation) {
        try {
            institutFormationRepository.save(institutFormation);
        } catch (JpaException ex) {
            throw new JpaException("Failed to create ", ex);
        }
    }

    @Override
    public List<InstitutFormation> getAll() {
        try {
            return institutFormationRepository.findAll();
        } catch (JpaException ex) {
            throw new JpaException("Failed to get all ", ex);
        }
    }

}
