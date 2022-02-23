package fr.ut1.miage.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Numero implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CodeNO")
    private int code;

    @Column(name = "DateNO")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "CodePU", nullable = false)
    private Publication publication;

}
