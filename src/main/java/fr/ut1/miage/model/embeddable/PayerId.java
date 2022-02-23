package fr.ut1.miage.model.embeddable;

import fr.ut1.miage.model.Pays;
import fr.ut1.miage.model.Publication;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class PayerId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "CodePU")
    private Publication publication;

    @ManyToOne
    @JoinColumn(name = "CodeP")
    private Pays pays;
}
