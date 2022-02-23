package fr.ut1.miage.service.impl;

import fr.ut1.miage.model.TypeJour;
import fr.ut1.miage.repository.TypeJourRepository;
import fr.ut1.miage.service.TypeJourService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TypeJourServiceImpl implements TypeJourService {

    private final TypeJourRepository typeJourRepository;

    @Override
    public void create(TypeJour typeJour) {
        try {
            typeJourRepository.save(typeJour);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
