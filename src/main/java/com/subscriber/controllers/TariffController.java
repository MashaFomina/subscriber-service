package com.subscriber.controllers;

import com.subscriber.models.Tariff;
import com.subscriber.services.TariffService;
import com.subscriber.utils.ResponseHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "/api/tariffs")
@RequiredArgsConstructor
@Api(value = "/api/tariffs", description = "Tariff endpoints", tags = "tariffs")
public class TariffController {
    private final TariffService tariffService;

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get all tariffs endpoint")
    @ApiResponses({
            @ApiResponse(code = 200, responseContainer = "List", message = "A list of tariffs"),
            @ApiResponse(code = 500, response = String.class, message = "Server exception")
    })
    public ResponseEntity<List<Tariff>> getAllTariffs() throws Exception {
        try {
            return ResponseHelper.getOkResponseWithBody(tariffService.getAllTariffs());
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseHelper.getStatusResponseWithBody(
                    HttpStatus.UNPROCESSABLE_ENTITY, Collections.emptyList());
        }
    }
}
