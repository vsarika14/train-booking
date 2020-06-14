package com.sarika.trainbooking.controller;

import com.sarika.trainbooking.model.AvailabilityInfoResponse;
import com.sarika.trainbooking.model.ReservationRequest;
import com.sarika.trainbooking.model.ReservationResponse;
import com.sarika.trainbooking.service.IReservationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@Slf4j
@CrossOrigin("*")
public class ReservationController {
    @Autowired
    IReservationService reservationService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<String> info() {
        log.info("Inside info...");
        return new ResponseEntity<>("Welcome to Sarika's IRCTC", HttpStatus.OK);
    }

    @GetMapping(value = "/trains/{trainid}/check-availability",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AvailabilityInfoResponse>> getDetails(@PathVariable("trainid") int trainId) throws Exception {
        return new ResponseEntity<List<AvailabilityInfoResponse>>(reservationService.checkAvailability(trainId),
                HttpStatus.OK);
    }

    @PostMapping(
            value = "/customers/{customerid}/reserve",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<ReservationResponse> createReservation(
            @PathParam("customerid") Integer customerId, @RequestBody @Validated ReservationRequest reservationRequest)
            throws Exception {
        return new ResponseEntity<>(reservationService.reserve(customerId, reservationRequest),
                HttpStatus.OK);
    }
}
