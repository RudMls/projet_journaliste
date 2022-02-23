package fr.ut1.miage.model;


import fr.ut1.miage.model.embeddable.AbonnerId;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class Abonner implements Serializable {

    @EmbeddedId
    private AbonnerId id;

}
