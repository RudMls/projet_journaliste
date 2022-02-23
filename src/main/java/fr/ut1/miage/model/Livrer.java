package fr.ut1.miage.model;

import fr.ut1.miage.model.embeddable.LivrerId;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Livrer {

    @EmbeddedId
    private LivrerId id;

}
