package naayadaa.service;


import naayadaa.dto.ClientDTO;
import naayadaa.dto.SearchCriteria;
import naayadaa.exception.JournalServiceException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Created by AnastasiiaDepenchuk on 24-Apr-18.
 */
@Service
public class ClientJournalService{

    private final RestTemplate restTemplate;

    private static final Logger LOGGER = LogManager.getLogger(ClientJournalService.class);

    @Autowired
    private AccessTokenService accessTokenService;

    @Value("${clientJournalResource.uri}")
    private String uri;

    public ClientJournalService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder
                .rootUri(uri)
                .build();   //set client HttpFactory
    }

    public String testRequest() throws JournalServiceException {
        HttpEntity<String> entity = new HttpEntity<>(null, getHttpHeaders());

        try {
            ResponseEntity<String> responseEntity = restTemplate.exchange(uri + "/test", HttpMethod.GET, entity, String.class);
            return responseEntity.getBody();
        } catch (RestClientException e) {
            LOGGER.error("Error while connecting clientJournalResource", e);
            throw new JournalServiceException("Journal service is unavailable. Try again later", e);
        }
    }

    public List<ClientDTO> getClients() throws JournalServiceException{
        HttpEntity<List<ClientDTO>> entity = new HttpEntity<>(null, getHttpHeaders());

        try {
            ResponseEntity<List<ClientDTO>> responseEntity = restTemplate.exchange(uri + "/clients", HttpMethod.GET, entity, new ParameterizedTypeReference<List<ClientDTO>>(){});

            return responseEntity.getBody();
        } catch (RestClientException e) {
            LOGGER.error("Error while connecting clientJournalResource", e);
            throw new JournalServiceException("Journal service is unavailable. Try again later", e);
        }
    }

    public ClientDTO get(Integer id) throws JournalServiceException {
        HttpEntity<ClientDTO> entity = new HttpEntity<>(null, getHttpHeaders());

        try {
            ResponseEntity<ClientDTO> responseEntity = restTemplate.exchange(uri + "/clients/{id}", HttpMethod.GET, entity, ClientDTO.class, id);

            return responseEntity.getBody();
        } catch (RestClientException e) {
            LOGGER.error("Error while connecting clientJournalResource", e);
            throw new JournalServiceException("Journal service is unavailable. Try again later", e);
        }
    }

    public ClientDTO create(ClientDTO clientDTO) throws JournalServiceException {
        HttpEntity<ClientDTO> entity = new HttpEntity<>(clientDTO, getHttpHeaders());

        try {
            ResponseEntity<ClientDTO> responseEntity = restTemplate.exchange(uri + "/clients", HttpMethod.POST, entity, ClientDTO.class);

            return responseEntity.getBody();
        } catch (RestClientException e) {
            LOGGER.error("Error while connecting clientJournalResource", e);
            throw new JournalServiceException("Journal service is unavailable. Try again later", e);
        }
    }

    public ClientDTO update(ClientDTO clientDTO) throws JournalServiceException {
        HttpEntity<ClientDTO> entity = new HttpEntity<>(clientDTO, getHttpHeaders());

        try {
            ResponseEntity<ClientDTO> responseEntity = restTemplate.exchange(uri + "/clients/{id}", HttpMethod.PUT, entity, ClientDTO.class, clientDTO.getId());

            return responseEntity.getBody();
        } catch (RestClientException e) {
            LOGGER.error("Error while connecting clientJournalResource", e);
            throw new JournalServiceException("Journal service is unavailable. Try again later", e);
        }
    }

    public void delete(Integer id) throws JournalServiceException {
        HttpEntity<ClientDTO> entity = new HttpEntity<>(null, getHttpHeaders());

        try {
            ResponseEntity<ClientDTO> responseEntity = restTemplate.exchange(uri + "/clients/{id}", HttpMethod.DELETE, entity, ClientDTO.class, id);

        } catch (RestClientException e) {
            LOGGER.error("Error while connecting clientJournalResource", e);
            throw new JournalServiceException("Journal service is unavailable. Try again later", e);
        }
    }

    public List<ClientDTO> searchClients(List<SearchCriteria> searchCriteriaList) throws JournalServiceException {
        HttpEntity<List<SearchCriteria>> entity = new HttpEntity<>(searchCriteriaList, getHttpHeaders());

        try {
            ResponseEntity<List<ClientDTO>> responseEntity = restTemplate.exchange(uri + "/clients/search", HttpMethod.POST, entity, new ParameterizedTypeReference<List<ClientDTO>>(){});

            return responseEntity.getBody();
        } catch (RestClientException e) {
            LOGGER.error("Error while connecting clientJournalResource", e);
            throw new JournalServiceException("Journal service is unavailable. Try again later", e);
        }
    }

    private HttpHeaders getHttpHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization","Bearer " + accessTokenService.getCurrentUserAccessToken());
        return headers;
    }

}
