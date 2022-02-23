package fr.ut1.miage.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor @AllArgsConstructor
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CodeC")
    private int code;

    @Column(name = "NomC")
    private String nom;

    @Column(name = "PrenomC")
    private String prenom;

    @ManyToOne
    @JoinColumn(name = "CodeV")
    private Ville ville;

    public Client(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    public Client(String nom, String prenom, Ville ville) {
        this.nom = nom;
        this.prenom = prenom;
        this.ville = ville;
    }

}
