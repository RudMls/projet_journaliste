package fr.ut1.miage.service.impl;

import fr.ut1.miage.exception.JpaException;
import fr.ut1.miage.model.CoutArticle;
import fr.ut1.miage.repository.CoutArticleRepository;
import fr.ut1.miage.service.CoutArticleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CoutArticleServiceImpl implements CoutArticleService {

    private final CoutArticleRepository coutArticleRepository;

    @Override
    public void create(CoutArticle coutArticle) {
        try {
            coutArticleRepository.save(coutArticle);
        } catch (JpaException ex) {
            throw new JpaException("Failed to create ", ex);
        }
    }

}