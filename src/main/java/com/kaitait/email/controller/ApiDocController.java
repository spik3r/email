package com.kaitait.email.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Controller
public class ApiDocController {

    @RequestMapping(value = "/apidoc", method = RequestMethod.GET)
    public String openApiDocumentation() {
        return "redirect:/v2/api-docs";
    }

    @RequestMapping(value = "/apidoc/ui", method = RequestMethod.GET)
    public String openApiUiDocumentation() {
        return "redirect:/swagger-ui.html";
    }
}
