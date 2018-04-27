package naayadaa.controller;


import naayadaa.exception.JournalServiceException;
import naayadaa.service.ClientJournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by AnastasiiaDepenchuk on 24-Apr-18.
 */
@RestController
@RequestMapping("/client-journal-admin-dashboard")
public class TestController {


    @Autowired
    private ClientJournalService clientJournalService;


    @RequestMapping(method = RequestMethod.GET, value = "/test")
    public String test() throws JournalServiceException {

        return clientJournalService.testRequest();
    }
}

