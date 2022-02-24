package fr.ut1.miage.service;

import fr.ut1.miage.model.InstitutFormation;

import java.util.List;

public interface InstitutFormationService {

    void create(InstitutFormation institutFormation);
    List<InstitutFormation> getAll();

}
