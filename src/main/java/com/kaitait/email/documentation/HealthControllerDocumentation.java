package com.kaitait.email.documentation;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;

public interface HealthControllerDocumentation {

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "I'm alive!"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    ResponseEntity<String> health();
}