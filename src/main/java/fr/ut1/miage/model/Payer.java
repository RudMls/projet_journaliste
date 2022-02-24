package fr.ut1.miage.model;

import fr.ut1.miage.model.embeddable.PayerId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@Builder
@NoArgsConstructor @AllArgsConstructor
public class Payer implements Serializable {

    @EmbeddedId
    private PayerId id;
}
