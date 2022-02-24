package fr.ut1.miage.repository;

import fr.ut1.miage.model.EcrireArticle;
import fr.ut1.miage.model.embeddable.EcrireArticleId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EcrireArticleRepository extends JpaRepository<EcrireArticle, EcrireArticleId> {
}
