package naayadaa.service.impl;

import naayadaa.dto.ClientDTO;
import naayadaa.exception.ClientResourceError;
import naayadaa.exception.ClientResourceException;


import java.util.List;

/**
 * Created by AnastasiiaDepenchuk on 27-Apr-18.
 */
public interface ClientService {

    List<ClientDTO> getClients() throws ClientResourceError;

    ClientDTO get(Integer id) throws ClientResourceException, ClientResourceError;

    ClientDTO create(ClientDTO clientDTO) throws ClientResourceError;

    ClientDTO update(ClientDTO clientDTO) throws ClientResourceError;

    void delete(Integer id) throws ClientResourceException, ClientResourceError;

}
