package fr.ut1.miage.service.impl;

import fr.ut1.miage.model.InstitutFormation;
import fr.ut1.miage.repository.InstitutFormationRepository;
import fr.ut1.miage.service.InstitutFormationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InstitutFormationServiceImpl implements InstitutFormationService {

    private final InstitutFormationRepository institutFormationRepository;

    @Override
    public void create(InstitutFormation institutFormation) {
        institutFormationRepository.save(institutFormation);
    }
}
