package fr.ut1.miage.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Builder
@NoArgsConstructor @AllArgsConstructor
public class CentreDistributeur implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CodeCD")
    private int code;

    @Column(name = "NomCD")
    private String nom;

    @Column(name = "VilleCD")
    private String ville;

    @ManyToOne
    @JoinColumn(name = "CodeP", nullable = false)
    private Pays pays;

}
