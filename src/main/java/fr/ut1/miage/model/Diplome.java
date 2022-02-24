package fr.ut1.miage.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Builder
@NoArgsConstructor @AllArgsConstructor
public class Diplome implements Serializable {

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
