package fr.ut1.miage.service;

import fr.ut1.miage.model.Diplome;

import java.util.List;

public interface DiplomeService {

    void create(Diplome diplome);
    List<Diplome> getAll();

}
