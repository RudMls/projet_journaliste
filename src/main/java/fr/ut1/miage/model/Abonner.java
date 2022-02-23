package fr.ut1.miage.model;


import fr.ut1.miage.model.embeddable.AbonnerId;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Abonner {

    @EmbeddedId
    private AbonnerId id;

}
