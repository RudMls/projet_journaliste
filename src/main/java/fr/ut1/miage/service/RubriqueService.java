package fr.ut1.miage.service;

import fr.ut1.miage.model.Rubrique;

import java.util.List;

public interface RubriqueService {

    void create(Rubrique rubrique);
    List<Rubrique> getAll();

}
