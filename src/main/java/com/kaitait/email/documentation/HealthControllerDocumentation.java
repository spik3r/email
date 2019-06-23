package com.kaitait.email.documentation;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public interface HealthControllerDocumentation {

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "I'm alive!"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @GetMapping("/health")
    ResponseEntity<String> health();
}