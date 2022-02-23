package fr.ut1.miage.service;

import fr.ut1.miage.model.Ville;

import java.util.List;

public interface VilleService {

    void create(Ville ville);
    List<Ville> getAll();

}
