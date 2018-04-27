package naayadaa.service.service;

import naayadaa.dto.ClientDTO;
import naayadaa.exception.ClientNotFound;
import naayadaa.exception.ClientResourceError;
import naayadaa.exception.ClientResourceException;
import naayadaa.model.Client;
import naayadaa.modelmapping.ModelMapperService;
import naayadaa.repository.ClientRepository;
import naayadaa.service.impl.ClientService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by AnastasiiaDepenchuk on 27-Apr-18.
 */
@Service
public class ClientServiceImpl implements ClientService {

    private final static Logger LOG = LogManager.getLogger(ClientServiceImpl.class);

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ModelMapperService modelMapperService;

    @Override
    public List<ClientDTO> getClients() throws ClientResourceError {
        try{
        List<Client> clients = clientRepository.findAll();
        return clients.stream().map( c -> modelMapperService.map(c, ClientDTO.class)).collect(Collectors.toList());

        }catch (DataAccessException e){
            LOG.error(e);
            throw new ClientResourceError("An exception while client retrieving occurred. Try again later");
        }
    }

    @Override
    public ClientDTO get(Integer id) throws ClientResourceException, ClientResourceError {
        Assert.notNull(id, "Id cannot be null");
        try {
            Client client = clientRepository.findOne(id);

            if (client == null) {
                throw new ClientNotFound("A client with this id does not exist");
            }

            return modelMapperService.map(client, ClientDTO.class);
        }catch (DataAccessException e){
            LOG.error(e);
            throw new ClientResourceError("An exception while client retrieving occurred");
        }
    }

    @Override
    public ClientDTO create(ClientDTO clientDTO) throws ClientResourceError {
        Assert.notNull(clientDTO, "The client dto cannot be null");
        clientDTO.setId(null);
        Client client = modelMapperService.map(clientDTO, Client.class);

        try {
            client = clientRepository.save(client);

            return modelMapperService.map(client, ClientDTO.class);
        }catch (DataAccessException e){
            LOG.error(e);
            throw new ClientResourceError("An exception while client creating occurred");
        }

    }

    @Override
    public ClientDTO update(ClientDTO clientDTO) throws ClientResourceError {
        Assert.notNull(clientDTO, "The client dto cannot be null");

        try {
            Client client = clientRepository.findOne(clientDTO.getId());
            //if there is no client with this id, remove id to be set by db and save a new client
            if(client == null) {
                clientDTO.setId(null);
            }
            client = modelMapperService.map(clientDTO, Client.class);
            client = clientRepository.save(client);

            return modelMapperService.map(client, ClientDTO.class);
        }catch (DataAccessException e){
            LOG.error(e);
            throw new ClientResourceError("An exception while client update occurred");
        }
    }

    @Override
    public void delete(Integer id) throws ClientResourceException, ClientResourceError {
        try {
            Client client = clientRepository.findOne(id);

            if (client == null) {
                throw new ClientNotFound("A client with this id does not exist");
            }

            clientRepository.delete(id);

        }catch (DataAccessException e){
            LOG.error(e);
            throw new ClientResourceError("An exception while client update occurred");
        }
    }
}
