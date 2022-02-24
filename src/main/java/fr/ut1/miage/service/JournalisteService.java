package fr.ut1.miage.service;

import fr.ut1.miage.model.Journaliste;

import java.util.List;

public interface JournalisteService {

    void create(Journaliste journaliste);
    List<Journaliste> getAll();

}
