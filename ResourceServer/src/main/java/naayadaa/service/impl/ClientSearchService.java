package naayadaa.service.impl;

import naayadaa.dto.ClientDTO;
import naayadaa.exception.ClientResourceError;
import naayadaa.search.SpecSearchCriteria;

import java.util.List;

/**
 * Created by AnastasiiaDepenchuk on 27-Apr-18.
 */
public interface ClientSearchService {

    List<ClientDTO> searchClients(final List<SpecSearchCriteria> params) throws ClientResourceError;

}
