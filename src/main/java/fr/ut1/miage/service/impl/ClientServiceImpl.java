package fr.ut1.miage.service.impl;

import fr.ut1.miage.exception.JpaException;
import fr.ut1.miage.model.Client;
import fr.ut1.miage.repository.ClientRepository;
import fr.ut1.miage.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    public boolean existsByNomAndPrenom(String nom, String prenom) {
        return clientRepository.existsByNomAndPrenom(nom, prenom);
    }

    @Override
    public void create(Client client) {
        try {
            clientRepository.save(client);
        } catch (JpaException ex) {
            throw new JpaException("Failed to create ", ex);
        }
    }

    @Override
    public List<Client> getAll() {
        try {
            return clientRepository.findAll();
        } catch (JpaException ex) {
            throw new JpaException("Failed to get all clients", ex);
        }
    }

}
