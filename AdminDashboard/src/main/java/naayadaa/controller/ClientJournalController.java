package naayadaa.controller;

import naayadaa.dto.ClientDTO;
import naayadaa.exception.JournalServiceException;
import naayadaa.service.ClientJournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by AnastasiiaDepenchuk on 24-Apr-18.
 */
@RestController
@RequestMapping("/client-journal-admin-dashboard")
public class ClientJournalController {

    @Autowired
    private ClientJournalService clientJournalService;

    @RequestMapping(method = RequestMethod.GET, value = "/clients")
    public List<ClientDTO> getJournal() throws JournalServiceException {

        return clientJournalService.getClients();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/clients/{id}")
    public ClientDTO getById(@PathVariable Integer id) throws JournalServiceException {

        return clientJournalService.get(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/clients")
    public ClientDTO create(@RequestBody ClientDTO clientDTO) throws JournalServiceException {

        return clientJournalService.create(clientDTO);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/clients/{id}")
    public ClientDTO update(@RequestBody ClientDTO clientDTO, @PathVariable Integer id) throws JournalServiceException {

        clientDTO.setId(id);
        return clientJournalService.update(clientDTO);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/clients/{id}")
    public void delete(@PathVariable Integer id) throws JournalServiceException {

        clientJournalService.delete(id);
    }
}
