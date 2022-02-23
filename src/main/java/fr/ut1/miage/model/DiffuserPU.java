package fr.ut1.miage.model;

import fr.ut1.miage.model.embeddable.DiffuserPUId;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class DiffuserPU implements Serializable {

    @EmbeddedId
    private DiffuserPUId id;

    @Column(name = "NbDiffusions")
    private int nombreDiffusions;

}
