package com.subscriber.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;
import springfox.documentation.annotations.ApiIgnore;

@Controller
public class MainController {

    @GetMapping(value = "/")
    @ApiIgnore
    public RedirectView showHomePageMock() {
        return new RedirectView("/swagger-ui.html");
    }

//    @GetMapping(value = "/logout")
//    @ApiIgnore
//    public RedirectView showLogoutPageMock() {
//        return new RedirectView("/login");
//    }
}
