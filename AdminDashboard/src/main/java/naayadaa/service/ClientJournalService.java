package naayadaa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * Created by AnastasiiaDepenchuk on 24-Apr-18.
 */
@Service
public class ClientJournalService {

    private final RestTemplate restTemplate;

    @Autowired
    private AccessTokenService accessTokenService;

    @Value("${clientJournalResource.uri}")
    private String uri;

    public ClientJournalService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder
                .rootUri(uri)
                .build();   //set client HttpFactory
    }

    public String testRequest(){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization","Bearer " + accessTokenService.getCurrentUserAccessToken());
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);


        ResponseEntity<String> responseEntity = restTemplate.exchange(uri + "/test", HttpMethod.GET, entity, String.class);
        return responseEntity.getBody();
    }
}
