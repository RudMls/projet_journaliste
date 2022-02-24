package fr.ut1.miage.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Builder
@NoArgsConstructor @AllArgsConstructor
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
