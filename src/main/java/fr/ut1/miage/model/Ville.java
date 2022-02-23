package fr.ut1.miage.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor @AllArgsConstructor
public class Ville implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CodeV")
    private int code;

    @Column(name = "NomV")
    private String nom;

    @ManyToOne
    @JoinColumn(name = "CodeCD", nullable = false)
    private CentreDistributeur centreDistributeur;

    public Ville(String nom, CentreDistributeur centreDistributeur) {
        this.nom = nom;
        this.centreDistributeur = centreDistributeur;
    }

}
