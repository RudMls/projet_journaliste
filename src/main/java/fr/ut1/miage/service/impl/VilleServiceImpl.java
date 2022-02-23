package fr.ut1.miage.service.impl;

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
        villeRepository.save(ville);
    }

    @Override
    public List<Ville> getAll() {
        return villeRepository.findAll();
    }

}
