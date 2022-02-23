package fr.ut1.miage.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Publication implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CodePU")
    private int code;

    @Column(name = "NomPU")
    private String nom;

    @Column(name = "DateCreatPU")
    private Date dateCreation;

    @Column(name = "GroupePresse")
    private String groupePresse;

    @Column(name = "DateFinPU")
    private Date dateFin;

    @Column(name = "Periodicite")
    private String periodicite;

    @ManyToOne
    @JoinColumn(name = "CodeJ", nullable = false)
    private Journaliste journaliste;

    @ManyToOne
    @JoinColumn(name = "CodeTY", nullable = false)
    private TypePU typePU;

}
