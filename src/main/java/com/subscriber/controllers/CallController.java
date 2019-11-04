package com.subscriber.controllers;

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

import com.subscriber.models.Call;
import com.subscriber.services.CallService;
import com.subscriber.utils.ResponseHelper;

@RestController
@RequestMapping(value = "/api/calls")
@RequiredArgsConstructor
@Validated
@Api(value = "/api/calls", description = "Call endpoints", tags = "call")
public class CallController {
    private final CallService callService;

    @PostMapping(value = "/make_call", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Make call on phone number")
    @ApiResponses({
            @ApiResponse(code = 200, response = Call.class, message = "A call info"),
            @ApiResponse(code = 400, response = String.class, message = "Invalid body of request"),
            @ApiResponse(code = 402, response = String.class, message = "Not enough money on balance"),
            @ApiResponse(code = 404, response = String.class, message = "Subscriber not found"),
            @ApiResponse(code = 429, response = String.class, message = "Caller exceeded limit on calls per day")
    })
    public ResponseEntity<Call> makeCall(
            @ApiParam(name = "callerPhone", value = "Caller phone number")
            @NotEmpty @RequestParam(value = "callerPhone", required = true) String callerPhone,
            @ApiParam(name = "receiverPhone", value = "Receiver phone number")
            @NotEmpty @RequestParam(value = "receiverPhone", required = true) String receiverPhone,
            @ApiParam(name = "duration", value = "Call duration in minutes")
            @RequestParam(value = "duration", required = true) @Min(value = 0, message = "duration of call can not be negative") float duration) throws Exception {
        if (callerPhone.equals(receiverPhone)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You can not call on your own phone number.");
        }
        return ResponseHelper.getOkResponseWithBody(callService.makeCall(callerPhone, receiverPhone, duration));
    }
}
