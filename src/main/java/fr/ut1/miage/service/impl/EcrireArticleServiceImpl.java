package fr.ut1.miage.service.impl;

import fr.ut1.miage.exception.JpaException;
import fr.ut1.miage.model.EcrireArticle;
import fr.ut1.miage.repository.EcrireArticleRepository;
import fr.ut1.miage.service.EcrireArticleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EcrireArticleServiceImpl implements EcrireArticleService {

    private final EcrireArticleRepository ecrireArticleRepository;

    @Override
    public void create(EcrireArticle ecrireArticle) {
        try {
            ecrireArticleRepository.save(ecrireArticle);
        } catch (JpaException ex) {
            throw new JpaException("Failed to create ", ex);
        }
    }

}
