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
@Check(constraints = "NomIF IN (" + Constant.CONSTRAINT_NOMIF_STR + ")")
public class InstitutFormation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CodeIF")
    private int code;

    @Column(name = "NomIF")
    private String nom;

}
