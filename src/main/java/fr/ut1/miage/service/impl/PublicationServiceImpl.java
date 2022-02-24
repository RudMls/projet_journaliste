package fr.ut1.miage.service.impl;

import fr.ut1.miage.exception.JpaException;
import fr.ut1.miage.model.Publication;
import fr.ut1.miage.repository.PublicationRepository;
import fr.ut1.miage.service.PublicationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PublicationServiceImpl implements PublicationService {

    private final PublicationRepository publicationRepository;

    @Override
    public void create(Publication publication) {
        try {
            publicationRepository.save(publication);
        } catch (JpaException ex) {
            throw new JpaException("Failed to create Publication", ex);
        }
    }

    @Override
    public List<Publication> getAll() {
        try {
            return publicationRepository.findAll();
        } catch (JpaException ex) {
            throw new JpaException("Failed to get all publications", ex);
        }
    }
}
