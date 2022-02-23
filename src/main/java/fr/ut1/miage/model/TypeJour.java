package fr.ut1.miage.model;

import fr.ut1.miage.util.Constant;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

import javax.persistence.*;

@Entity
@NoArgsConstructor @AllArgsConstructor
@Check(constraints = "PrixCar > 0 and NomTYJ IN (" + Constant.CONSTRAINT_NOMTYJ_STR + ")")
public class TypeJour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CodeTYJ")
    private int code;

    @Column(name = "NomTYJ")
    private String nom;

    @Column(name = "PrixCar")
    private float prixCaractere;

    public TypeJour(String nom, float prixCaractere) {
        this.nom = nom;
        this.prixCaractere = prixCaractere;
    }
}
