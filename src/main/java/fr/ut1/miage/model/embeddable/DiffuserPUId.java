package fr.ut1.miage.model.embeddable;

import fr.ut1.miage.model.Publication;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@Builder
@NoArgsConstructor @AllArgsConstructor
public class DiffuserPUId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "CodePU")
    private Publication publication;

    @Column(name = "Annee")
    private int annee;

}
