package fr.ut1.miage.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor @AllArgsConstructor
public class Pays implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CodeP")
    private int code;

    @Column(name = "NomP")
    private String nom;

    public Pays(String nom) {
        this.nom = nom;
    }
}
