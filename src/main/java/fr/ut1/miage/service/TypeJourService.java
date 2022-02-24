package fr.ut1.miage.service;

import fr.ut1.miage.model.TypeJour;

import java.util.List;

public interface TypeJourService {

    void create(TypeJour typeJour);
    List<TypeJour> getAll();

}
