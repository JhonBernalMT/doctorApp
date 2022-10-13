package com.doctorapp.doctorapp.service;

import com.doctorapp.doctorapp.model.Client;
import com.doctorapp.doctorapp.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAll() {
        return clientRepository.getAll();
    }

    public Optional<Client> getById(Integer idClient) {
        return clientRepository.getById(idClient);
    }

    public Client save(Client client) {
        if (client.getIdClient() == null) {
            return clientRepository.save(client);
        } else {
            Optional<Client> optionalClient = clientRepository.getById(client.getIdClient());
            if (optionalClient.isEmpty()) {
                return clientRepository.save(client);
            } else {
                return client;
            }
        }
    }

    public Client update(Client client) {
        if (client.getIdClient() != null) {
            Optional<Client> optionalClient = clientRepository.getById(client.getIdClient());
            if (!optionalClient.isEmpty()) {     //! == Niega la funcion if
                if (client.getName() != null) {
                    optionalClient.get().setName(client.getName());
                }
                if (client.getEmail() != null) {
                    optionalClient.get().setEmail(client.getEmail());
                }
                if (client.getAge() != null) {
                    optionalClient.get().setAge(client.getAge());
                }
                if (client.getPassword() != null) {
                    optionalClient.get().setPassword(client.getPassword());
                }
                clientRepository.save(optionalClient.get());
                return optionalClient.get();
            } else {
                return client;
            }
        } else {
            return client;
        }
    }

    public boolean delete(Integer idClient) {
        Boolean aBoolean = getById(idClient).map(client -> {
            clientRepository.delete(client);
            return true;
        }).orElse(false);
        return aBoolean;
    }
}
