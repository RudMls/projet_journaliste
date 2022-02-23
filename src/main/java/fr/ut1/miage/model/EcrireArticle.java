package fr.ut1.miage.model;

import fr.ut1.miage.model.embeddable.EcrireArticleId;
import org.hibernate.annotations.Check;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@Check(constraints = "NbCar > 0 and NbArt > 0")
public class EcrireArticle implements Serializable {

    @EmbeddedId
    private EcrireArticleId id;

    @Column(name = "NbCar")
    private int nombreCaractere;

    @Column(name = "NbArt")
    private int nombreArticle;

}
