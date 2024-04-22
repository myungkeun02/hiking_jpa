package org.myungkeun.hiking_jpa.controllers;

import lombok.RequiredArgsConstructor;
import org.myungkeun.hiking_jpa.dto.hiking.HikingDto;
import org.myungkeun.hiking_jpa.dto.hiking.HikingResponseDto;
import org.myungkeun.hiking_jpa.services.HikingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hiking")
@RequiredArgsConstructor

public class HikingController {
    private final HikingService hikingService;
    @GetMapping
    public ResponseEntity<List<HikingResponseDto>> searchHiking() {
        List<HikingResponseDto> result = hikingService.searchHiking();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HikingResponseDto> getHiking(
            @PathVariable(name = "id") Long id
    ) {
        HikingResponseDto result = hikingService.getHikingById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> registerHiking(
            @RequestBody HikingDto hikingDto
    ){
        String result =  hikingService.insertHiking(hikingDto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
