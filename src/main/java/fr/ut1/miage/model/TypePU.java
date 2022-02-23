package fr.ut1.miage.model;

import fr.ut1.miage.util.Constant;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor @AllArgsConstructor
@Check(constraints = "NomTY IN (" + Constant.CONSTRAINT_NOMTY_STR + ")")
public class TypePU implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CodeTY")
    private int code;

    @Column(name = "NomTY")
    private String nom;

    public TypePU(String nom) {
        this.nom = nom;
    }

}
