package fr.ut1.miage.model;

import javax.persistence.*;

@Entity
@Table(name = "Diplome")
public class Diplome {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CodeDI")
    private int code;

    @Column(name = "NomDI")
    private String nom;

    @ManyToOne
    @JoinColumn(name = "CodeIF", nullable = false)
    private InstitutFormation institutFormation;

}
