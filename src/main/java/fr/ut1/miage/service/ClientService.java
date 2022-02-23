package fr.ut1.miage.service;

import fr.ut1.miage.model.Client;

public interface ClientService {

    boolean existsByNomAndPrenom(String nom, String prenom);
    public abstract void create(Client client);
}
