package fr.ut1.miage.model;

import fr.ut1.miage.model.embeddable.LivrerId;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class Livrer implements Serializable {

    @EmbeddedId
    private LivrerId id;

}
