package fr.ut1.miage.model;

import fr.ut1.miage.model.embeddable.PayerId;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class Payer implements Serializable {

    @EmbeddedId
    private PayerId id;
}
