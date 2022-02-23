package fr.ut1.miage.service.impl;

import fr.ut1.miage.model.TypePU;
import fr.ut1.miage.repository.TypePURepository;
import fr.ut1.miage.service.TypePUService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TypePUServiceImpl implements TypePUService {

    private final TypePURepository typePURepository;

    @Override
    public void create(TypePU typePU) {
        try {
            typePURepository.save(typePU);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
