package naayadaa.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import naayadaa.AbstractIntegrationTest;
import naayadaa.dto.ClientDTO;
import naayadaa.model.Client;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by AnastasiiaDepenchuk on 29-Apr-18.
 */
@ActiveProfiles("test")
@DatabaseSetup(value = {"classpath:data.xml"})
@ExpectedDatabase(value = "classpath:data.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
@DatabaseTearDown(value = {"classpath:tear_down.xml"})
public class ClientJournalControllerGet_IT extends AbstractIntegrationTest {

    @Resource
    private FilterChainProxy springSecurityFilterChain;

    @Resource
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    private Map<Integer, ClientDTO> clientDTOS = new HashMap<>();

    @Before
    public void setup() throws IOException {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).addFilter(springSecurityFilterChain).build();
        List<ClientDTO> clientDTOList = objectMapper.readValue( this.getClass().getClassLoader()
                .getResourceAsStream("clients.json"), new TypeReference<List<ClientDTO>>(){});
        for(ClientDTO clientDTO: clientDTOList){
            clientDTOS.put(clientDTO.getId(), clientDTO);
        }
    }

    /*
   * A not logged-in user trying to access to the endpoint will receive a 401 HTTP error
   */
    @Test
    public void getClients_WithUnauthorisedUser_Test_OK() throws Exception {
        mockMvc.perform(get("/client-journal-resource/clients"))
                .andExpect(status().is(401));
    }


    @Test
    public void getAllClients_Test_OK() throws Exception {
        RequestPostProcessor bearerToken = addBearerToken("test");

        MvcResult result = mockMvc.perform(get("/client-journal-resource/clients")
                .with(bearerToken))
                .andExpect(status().is(200)).andReturn();

        List<ClientDTO> clients = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<ClientDTO>>(){});
        Assert.assertEquals(4, clients.size());
        for(ClientDTO client : clients){
            Assert.assertEquals(clientDTOS.get(client.getId()).getFirstName(), client.getFirstName());
            Assert.assertEquals(clientDTOS.get(client.getId()).getEmail(), client.getEmail());
            Assert.assertEquals(clientDTOS.get(client.getId()).getLastName(), client.getLastName());
        }
    }

    @Test
    public void getClient_Test_OK() throws Exception {
        RequestPostProcessor bearerToken = addBearerToken("test", "read");

        MvcResult result = mockMvc.perform(get("/client-journal-resource/clients/{id}", 1)
                .with(bearerToken))
                .andExpect(status().is(200)).andReturn();

        ClientDTO client = objectMapper.readValue(result.getResponse().getContentAsString(), ClientDTO.class);

        Assert.assertEquals(clientDTOS.get(client.getId()).getFirstName(), client.getFirstName());
        Assert.assertEquals(clientDTOS.get(client.getId()).getEmail(), client.getEmail());
        Assert.assertEquals(clientDTOS.get(client.getId()).getLastName(), client.getLastName());

    }
}
