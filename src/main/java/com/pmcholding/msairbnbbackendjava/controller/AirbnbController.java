package com.pmcholding.msairbnbbackendjava.controller;

import com.pmcholding.msairbnbbackendjava.domain.Airbnb;
import com.pmcholding.msairbnbbackendjava.domain.Dashboard;
import com.pmcholding.msairbnbbackendjava.service.AirbnbService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/airbnb")
@AllArgsConstructor
public class AirbnbController {

    private final AirbnbService airbnbService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Airbnb>> findAllAirbnb(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        log.info("Getting all airbnb...");
        return ResponseEntity.status(HttpStatus.OK).body(airbnbService.findAll(pageable));
    }

    @GetMapping(value = "/dashboard", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dashboard> getDashboard() {
        log.info("Getting dashboard...");
        return ResponseEntity.status(HttpStatus.OK).body(airbnbService.getDashboard());
    }
}
