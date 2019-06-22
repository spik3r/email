package com.kaitait.email.documentation;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.mail.MessagingException;

public interface EmailControllerDocumentation {

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 400, message = "Threw Exception as expected"),
    })
    @ApiOperation("Dummy endpoint for controller advice")
    @GetMapping({"/{shouldExplode}", "/"})
    ResponseEntity<String> index(@PathVariable(required = false) final String shouldExplode);

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 400, message = "Bad Request"),
    })
    @ApiOperation("Endpoint to test sending emails to a dummy user")
    @PostMapping("/mail")
    ResponseEntity<String> sendMail() throws MessagingException;
}