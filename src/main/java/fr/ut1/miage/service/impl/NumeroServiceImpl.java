package fr.ut1.miage.service.impl;

import fr.ut1.miage.exception.JpaException;
import fr.ut1.miage.model.Numero;
import fr.ut1.miage.repository.NumeroRepository;
import fr.ut1.miage.service.NumeroService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NumeroServiceImpl implements NumeroService {

    private final NumeroRepository numeroRepository;

    @Override
    public void create(Numero numero) {
        try {
            numeroRepository.save(numero);
        } catch (JpaException ex) {
            throw new JpaException("Failed to create ", ex);
        }
    }

    @Override
    public List<Numero> getAll() {
        try {
            return numeroRepository.findAll();
        } catch (JpaException ex) {
            throw new JpaException("Failed to get all numeros ", ex);
        }
    }

}
