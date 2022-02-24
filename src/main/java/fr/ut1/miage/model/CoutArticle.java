package fr.ut1.miage.model;

import fr.ut1.miage.model.embeddable.CoutArticleId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@Builder
@NoArgsConstructor @AllArgsConstructor
public class CoutArticle implements Serializable {

    @EmbeddedId
    private CoutArticleId id;

    @Column(name = "ForfaitArt")
    private int forfaitArticle;

}
