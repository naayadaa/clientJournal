package naayadaa.service;


import naayadaa.exception.JournalServiceException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * Created by AnastasiiaDepenchuk on 24-Apr-18.
 */
@Service
public class ClientJournalService {

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
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization","Bearer " + accessTokenService.getCurrentUserAccessToken());
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);


        try {
            ResponseEntity<String> responseEntity = restTemplate.exchange(uri + "/test", HttpMethod.GET, entity, String.class);
            return responseEntity.getBody();
        } catch (RestClientException e) {
            LOGGER.error("Error while connecting clientJournalResource", e);
            throw new JournalServiceException("Journal service is unavailable. Try again later", e);
        }
    }

}
