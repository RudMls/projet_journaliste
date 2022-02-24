package fr.ut1.miage.service;

import fr.ut1.miage.model.Client;

import java.util.List;

public interface ClientService {

    boolean existsByNomAndPrenom(String nom, String prenom);
    void create(Client client);
    List<Client> getAll();
}
