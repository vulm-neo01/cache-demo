package com.example.cachedemo.controller;

import com.example.cachedemo.model.Airport;
import com.example.cachedemo.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/airport-service/v1")
@RequiredArgsConstructor
public class AirportController {

    @Autowired
    private final AirportService airportService;

    @GetMapping("/code/{id}")
    public ResponseEntity<Airport> getAirport(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK).body(airportService.getCode(id));
    }

//    @GetMapping("/name/{id}")
//    public ResponseEntity<String> getName(@PathVariable String id){
//        return ResponseEntity.status(HttpStatus.OK).body(airportService.getName(id));
//    }

    @PostMapping()
    public ResponseEntity<Airport> postNew(@RequestBody Airport airport){
        return ResponseEntity.status(HttpStatus.OK).body(airportService.createNew(airport));
    }
}
