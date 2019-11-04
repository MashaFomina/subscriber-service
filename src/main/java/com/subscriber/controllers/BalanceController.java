package com.subscriber.controllers;

import com.subscriber.models.Balance;
import com.subscriber.services.SubscriberService;
import com.subscriber.utils.ResponseHelper;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/balance")
@RequiredArgsConstructor
@Validated
@Api(value = "/api/balance", description = "Balance endpoints", tags = "balance")
public class BalanceController {
    private final SubscriberService subscriberService;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get balance by subscriber id")
    @ApiResponses({
            @ApiResponse(code = 200, response = Balance.class, message = "A balance info"),
            @ApiResponse(code = 404, response = String.class, message = "Subscriber not found")
    })
    public ResponseEntity<?> getBalanceById(@PathVariable Long id) {
        return handleBalanceRequest(subscriberService.getBalanceById(id));
    }

    @GetMapping(value = "/phone/{phone}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get balance by phone number")
    @ApiResponses({
            @ApiResponse(code = 200, response = Balance.class, message = "A balance info"),
            @ApiResponse(code = 404, response = String.class, message = "Subscriber not found")
    })
    public ResponseEntity<?> getBalanceByPhone(@PathVariable String phone) {
        return handleBalanceRequest(subscriberService.getBalanceByPhone(phone));
    }

    private ResponseEntity<?> handleBalanceRequest(Optional<Balance> balance) {
        if (balance.isPresent()) {
            return ResponseHelper.getOkResponseWithBody(balance);
        }
        return ResponseHelper.getNotFoundResponse();
    }

    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Add money to balance by phone number")
    @ApiResponses({
            @ApiResponse(code = 200, response = Balance.class, message = "A balance info"),
            @ApiResponse(code = 400, response = String.class, message = "Invalid body of request"),
            @ApiResponse(code = 404, response = String.class, message = "Subscriber not found")
    })
    public ResponseEntity<Balance> addMoney(
            @ApiParam(name = "phone", value = "phone number", defaultValue = "+77770001122")
            @NotEmpty @RequestParam(value = "phone", required = true) String phone,
            @ApiParam(name = "money", value = "added money", defaultValue = "100")
            @RequestParam(value = "money", defaultValue = "100") @Min(value = 0, message = "amount of money must be positive") float money) throws Exception {
        return ResponseHelper.getOkResponseWithBody(subscriberService.addMoney(phone, money));
    }
}
