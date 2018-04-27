package naayadaa.service.service;

import naayadaa.dto.ClientDTO;
import naayadaa.exception.ClientResourceError;
import naayadaa.model.Client;
import naayadaa.modelmapping.ModelMapperService;
import naayadaa.repository.ClientRepository;
import naayadaa.search.ClientSpecificationsBuilder;
import naayadaa.search.SpecSearchCriteria;
import naayadaa.service.impl.ClientSearchService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by AnastasiiaDepenchuk on 27-Apr-18.
 */
@Service
public class ClientSearchServiceImpl implements ClientSearchService {

    private final static Logger LOG = LogManager.getLogger(ClientServiceImpl.class);

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ModelMapperService modelMapperService;

    @Override
    public List<ClientDTO> searchUser(final List<SpecSearchCriteria> params) throws ClientResourceError{
        try {
            ClientSpecificationsBuilder clientSpecificationsBuilder = new ClientSpecificationsBuilder();

            for (final SpecSearchCriteria param : params) {
                clientSpecificationsBuilder.with(param);
            }

            List<Client> clients = clientRepository.findAll(clientSpecificationsBuilder.build());

            return clients.stream().map(c -> modelMapperService.map(c, ClientDTO.class)).collect(Collectors.toList());
        }catch (DataAccessException e){
            LOG.error(e);
            throw new ClientResourceError("An exception while client retrieving occurred. Try again later");
        }
    }



}
