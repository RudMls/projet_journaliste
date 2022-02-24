package fr.ut1.miage.model;

import fr.ut1.miage.util.Constant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Builder
@NoArgsConstructor @AllArgsConstructor
@Check(constraints = "PrixCar > 0 and NomTYJ IN (" + Constant.CONSTRAINT_NOMTYJ_STR + ")")
public class TypeJour implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CodeTYJ")
    private int code;

    @Column(name = "NomTYJ")
    private String nom;

    @Column(name = "PrixCar")
    private float prixCaractere;

}
