package fr.ut1.miage.service;

import fr.ut1.miage.model.Numero;

import java.util.List;

public interface NumeroService {

    void create(Numero numero);
    List<Numero> getAll();

}
