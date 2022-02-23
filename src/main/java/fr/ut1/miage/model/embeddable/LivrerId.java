package fr.ut1.miage.model.embeddable;

import fr.ut1.miage.model.Client;
import fr.ut1.miage.model.Numero;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class LivrerId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "CodePU")
    private Numero numero;

    @ManyToOne
    @JoinColumn(name = "CodeP")
    private Client client;

}
