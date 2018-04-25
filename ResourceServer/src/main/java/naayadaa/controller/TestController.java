package naayadaa.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by AnastasiiaDepenchuk on 24-Apr-18.
 */
@RestController
@RequestMapping("/")
public class TestController {


    @PreAuthorize("#oauth2.hasScope('read')")
    @RequestMapping(method = RequestMethod.GET, value = "/test")
    public String test(){
        return "Hello admin";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/smb")
    public String test2(){
        return "Hello smb";
    }
}
