package fr.ut1.miage.model.embeddable;

import fr.ut1.miage.model.Publication;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class DiffuserPUId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "CodePU")
    private Publication publication;

    @Column(name = "Annee")
    private int annee;

}
