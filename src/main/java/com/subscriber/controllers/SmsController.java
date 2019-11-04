package com.subscriber.controllers;

import com.subscriber.models.Balance;
import com.subscriber.models.Sms;
import com.subscriber.services.SmsService;
import com.subscriber.services.SubscriberService;
import com.subscriber.utils.ResponseHelper;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/sms")
@RequiredArgsConstructor
@Validated
@Api(value = "/api/sms", description = "Sms endpoints", tags = "sms")
public class SmsController {
    private final SmsService smsService;

    @PostMapping(value = "/send", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Send sms")
    @ApiResponses({
            @ApiResponse(code = 200, response = Sms.class, message = "A sms info"),
            @ApiResponse(code = 400, response = String.class, message = "Invalid body of request"),
            @ApiResponse(code = 402, response = String.class, message = "Not enough money on balance"),
            @ApiResponse(code = 404, response = String.class, message = "Subscriber not found")
    })
    public ResponseEntity<Sms> sendSms(
            @ApiParam(name = "senderPhone", value = "Sender phone number")
            @NotEmpty @RequestParam(value = "senderPhone", required = true) String senderPhone,
            @ApiParam(name = "receiverPhone", value = "Receiver phone number")
            @NotEmpty @RequestParam(value = "receiverPhone", required = true) String receiverPhone,
            @ApiParam(name = "text", value = "Sms message")
            @NotEmpty @RequestParam(value = "text", required = true) @Size(max = 160) String text) throws Exception {
        if (senderPhone.equals(receiverPhone)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sender and receiver phones must be different.");
        }
        return ResponseHelper.getOkResponseWithBody(smsService.sendSms(senderPhone, receiverPhone, text));
    }
}
