package fr.ut1.miage.model;

import fr.ut1.miage.model.embeddable.CoutArticleId;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class CoutArticle {

    @EmbeddedId
    private CoutArticleId id;

    @Column(name = "ForfaitArt")
    private int forfaitArticle;

}
