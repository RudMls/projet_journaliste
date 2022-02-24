package fr.ut1.miage.service;

import fr.ut1.miage.model.TypePU;

import java.util.List;

public interface TypePUService {

    void create(TypePU typePU);
    List<TypePU> getAll();

}
