package fr.ut1.miage.service.impl;

import fr.ut1.miage.model.CentreDistributeur;
import fr.ut1.miage.repository.CentreDistributeurRepository;
import fr.ut1.miage.service.CentreDistributeurService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CentreDistributeurServiceImpl implements CentreDistributeurService {

    private final CentreDistributeurRepository centreDistributeurRepository;

    @Override
    public void create(CentreDistributeur centreDistributeur) {
        try {
            centreDistributeurRepository.save(centreDistributeur);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
