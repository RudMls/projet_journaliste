package fr.ut1.miage.service;

import fr.ut1.miage.model.Publication;

import java.util.List;

public interface PublicationService {

    void create(Publication publication);
    List<Publication> getAll();
}
