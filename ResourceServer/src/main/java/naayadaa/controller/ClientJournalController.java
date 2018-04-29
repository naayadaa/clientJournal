package naayadaa.controller;

import naayadaa.dto.ClientDTO;
import naayadaa.exception.ClientResourceError;
import naayadaa.exception.ClientResourceException;
import naayadaa.search.SpecSearchCriteria;
import naayadaa.service.impl.ClientSearchService;
import naayadaa.service.impl.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by AnastasiiaDepenchuk on 24-Apr-18.
 */
@RestController
@RequestMapping("/client-journal-resource")
public class ClientJournalController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientSearchService clientSearchService;


    @PreAuthorize("#oauth2.hasScope('read')")
    @RequestMapping(method = RequestMethod.POST, value = "/clients/search")
    public List<ClientDTO> search(@RequestBody List<SpecSearchCriteria> searchCriteriaList) throws ClientResourceError {
        return clientSearchService.searchClients(searchCriteriaList);
    }

    @PreAuthorize("#oauth2.hasScope('read')")
    @RequestMapping(method = RequestMethod.GET, value = "/clients")
    public List<ClientDTO> getJournal() throws ClientResourceError {

        return clientService.getClients();
    }

    @PreAuthorize("#oauth2.hasScope('read')")
    @RequestMapping(method = RequestMethod.GET, value = "/clients/{id}")
    public ClientDTO getById(@PathVariable Integer id) throws ClientResourceError, ClientResourceException {

        return clientService.get(id);
    }

    @PreAuthorize("#oauth2.hasScope('read') and #oauth2.hasScope('write')")
    @RequestMapping(method = RequestMethod.POST, value = "/clients")
    public ClientDTO create(@RequestBody ClientDTO clientDTO) throws ClientResourceError {

        return clientService.create(clientDTO);
    }

    @PreAuthorize("#oauth2.hasScope('read') and #oauth2.hasScope('write')")
    @RequestMapping(method = RequestMethod.PUT, value = "/clients/{id}")
    public ClientDTO update(@RequestBody ClientDTO clientDTO, @PathVariable Integer id) throws ClientResourceError {

        clientDTO.setId(id);
        return clientService.update(clientDTO);
    }

    @PreAuthorize("#oauth2.hasScope('read') and #oauth2.hasScope('write')")
    @RequestMapping(method = RequestMethod.DELETE, value = "/clients/{id}")
    public void delete(@PathVariable Integer id) throws ClientResourceError, ClientResourceException {

        clientService.delete(id);
    }
}
