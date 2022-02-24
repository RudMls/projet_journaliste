package fr.ut1.miage.repository;

import fr.ut1.miage.model.CoutArticle;
import fr.ut1.miage.model.embeddable.CoutArticleId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoutArticleRepository extends JpaRepository<CoutArticle, CoutArticleId> {
}
