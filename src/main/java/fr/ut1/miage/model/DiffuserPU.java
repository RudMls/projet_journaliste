package fr.ut1.miage.model;

import fr.ut1.miage.model.embeddable.DiffuserPUId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@Builder
@NoArgsConstructor @AllArgsConstructor
public class DiffuserPU implements Serializable {

    @EmbeddedId
    private DiffuserPUId id;

    @Column(name = "NbDiffusions")
    private int nombreDiffusions;

}
