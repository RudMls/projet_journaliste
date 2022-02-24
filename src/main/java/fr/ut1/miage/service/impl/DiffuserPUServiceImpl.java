package fr.ut1.miage.service.impl;

import fr.ut1.miage.exception.JpaException;
import fr.ut1.miage.model.DiffuserPU;
import fr.ut1.miage.repository.DiffuserPURepository;
import fr.ut1.miage.service.DiffuserPUService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DiffuserPUServiceImpl implements DiffuserPUService {

    private final DiffuserPURepository diffuserPURepository;

    @Override
    public void create(DiffuserPU diffuserPU) {
        try {
            diffuserPURepository.save(diffuserPU);
        } catch (JpaException ex) {
            throw new JpaException("Failed to create ", ex);
        }
    }

}
