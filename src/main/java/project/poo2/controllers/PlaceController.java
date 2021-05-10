package project.poo2.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import project.poo2.entities.Place;
import project.poo2.services.PlaceService;

@RestController
@RequestMapping("/places")
public class PlaceController {
    
    @Autowired
    private PlaceService placeService;

    @GetMapping
    public ResponseEntity<Page<Place>> getPlace(
        @RequestParam(value = "page", defaultValue = "0") Integer page,
        @RequestParam(value = "linesPerPage", defaultValue = "5") Integer linesPerPage,
        @RequestParam(value = "direction", defaultValue = "ASC") String direction,
        @RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
        @RequestParam(value = "name", defaultValue = "") String placeName,
        @RequestParam(value = "address", defaultValue = "") String placeAddress
    ) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),orderBy);
        Page<Place> list = placeService.getPlace(pageRequest, placeName, placeAddress);
        return ResponseEntity.ok(list);
    }
    
}
