package fr.ut1.miage.model.embeddable;

import fr.ut1.miage.model.Journaliste;
import fr.ut1.miage.model.Rubrique;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@Builder
@NoArgsConstructor @AllArgsConstructor
public class CoutArticleId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "CodeRU")
    private Rubrique rubrique;

    @ManyToOne
    @JoinColumn(name = "CodeJ")
    private Journaliste journaliste;

}
