package fr.ut1.miage.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Numero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CodeNO")
    private int code;

    @Column(name = "DateNO")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "CodePU", nullable = false)
    private Publication publication;

//    @ManyToMany(mappedBy = "numeros")
//    private List<Client> clients = new ArrayList<>();

}
