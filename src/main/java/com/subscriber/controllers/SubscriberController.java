package com.subscriber.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import com.subscriber.models.Subscriber;
import com.subscriber.services.SubscriberService;
import com.subscriber.utils.ResponseHelper;

@RestController
@RequestMapping(value = "/api/subscribers")
@RequiredArgsConstructor
@Api(value = "/api/subscribers", description = "Subscriber endpoints", tags = "subscribers")
public class SubscriberController {

    private final SubscriberService subscriberService;

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get all subscribers endpoint")
    @ApiResponses({
            @ApiResponse(code = 200, responseContainer = "List", message = "A set of subscribers"),
            @ApiResponse(code = 500, response = String.class, message = "Server exception")
    })
    public ResponseEntity<List<Subscriber>> getAllSubscribers() throws Exception {
        try {
            return ResponseHelper.getOkResponseWithBody(subscriberService.getAllSubscribers());
        } catch (Exception ex) {
            return ResponseHelper.getStatusResponseWithBody(
                    HttpStatus.UNPROCESSABLE_ENTITY, Collections.emptyList());
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get subscriber by id")
    @ApiResponses({
            @ApiResponse(code = 200, response = Subscriber.class, message = "A subscriber info"),
            @ApiResponse(code = 404, response = String.class, message = "Subscriber not found")
    })
    public ResponseEntity<?> getSubscriberById(@PathVariable Long id) {
        Optional<Subscriber> subscriber = subscriberService.getSubscriberById(id);
        if (subscriber.isPresent()) {
            return ResponseHelper.getOkResponseWithBody(subscriber);
        }
        return ResponseHelper.getNotFoundResponse();
    }

//    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Subscriber> deleteSubscriber(@PathVariable long id) {
//        subscriberService.deleteById(id);
//    }
}
