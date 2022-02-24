package fr.ut1.miage.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Builder @Getter
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

}
