package naayadaa.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import naayadaa.AbstractIntegrationTest;
import naayadaa.dto.ClientDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by AnastasiiaDepenchuk on 29-Apr-18.
 */
@ActiveProfiles("test")
@DatabaseSetup(value = {"classpath:data.xml"})
@DatabaseTearDown(value = {"classpath:tear_down.xml"})
public class ClientJournalControllerPost_IT extends AbstractIntegrationTest {

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
        List<ClientDTO> clientDTOList = objectMapper.readValue(this.getClass().getClassLoader()
                .getResourceAsStream("clients.json"), new TypeReference<List<ClientDTO>>() {
        });
        for (ClientDTO clientDTO : clientDTOList) {
            clientDTOS.put(clientDTO.getId(), clientDTO);
        }
    }

    /*
   * A not logged-in user trying to access to the endpoint will receive a 401 HTTP error
   */
    @Test
    @ExpectedDatabase(value = "classpath:data.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void createClient_WithUnauthorisedUser() throws Exception {
        ClientDTO postBody = clientDTOS.get(5);
        postBody.setId(null);
        mockMvc.perform(post("/client-journal-resource/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(postBody)))
                .andExpect(status().is(401));
    }


    @Test
    @ExpectedDatabase(value = "classpath:create_expected.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void createClient_Test_OK() throws Exception {
        ClientDTO postBody = clientDTOS.get(5);
        postBody.setId(null);

        RequestPostProcessor bearerToken = addBearerToken("test");

        MvcResult result = mockMvc.perform(post("/client-journal-resource/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(postBody))
                .with(bearerToken))
                .andExpect(status().is(200)).andReturn();
        ClientDTO client = objectMapper.readValue(result.getResponse().getContentAsString(), ClientDTO.class);

        Assert.assertEquals(postBody.getFirstName(), client.getFirstName());
        Assert.assertEquals(postBody.getEmail(), client.getEmail());
        Assert.assertEquals(postBody.getLastName(), client.getLastName());

    }

    @Test
    @ExpectedDatabase(value = "classpath:data.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void createClient_ValidationException() throws Exception {
        ClientDTO postBody = clientDTOS.get(5);
        postBody.setId(null);
        postBody.setEmail(null); //email cannot be null

        RequestPostProcessor bearerToken = addBearerToken("test");

        MvcResult result = mockMvc.perform(post("/client-journal-resource/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(postBody))
                .with(bearerToken))
                .andExpect(status().is(400)).andReturn();
    }

    @Test
    @ExpectedDatabase(value = "classpath:data.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void createClient_NotUniqueEmailException() throws Exception {
        ClientDTO postBody = clientDTOS.get(5);
        postBody.setId(null);
        postBody.setEmail("vasya@mail.com"); //email cannot be null

        RequestPostProcessor bearerToken = addBearerToken("test");

        MvcResult result = mockMvc.perform(post("/client-journal-resource/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(postBody))
                .with(bearerToken))
                .andExpect(status().is(400)).andReturn();
    }
}
