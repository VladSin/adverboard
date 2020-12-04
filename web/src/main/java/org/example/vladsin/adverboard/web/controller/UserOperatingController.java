package org.example.vladsin.adverboard.web.controller;

import org.example.vladsin.adverboard.model.Ad;
import org.example.vladsin.adverboard.model.Billboard;
import org.example.vladsin.adverboard.model.Location;
import org.example.vladsin.adverboard.service.repository.BillboardService;
import org.example.vladsin.adverboard.service.repository.GroupBillboardService;
import org.example.vladsin.adverboard.service.repository.LocationService;
import org.example.vladsin.adverboard.service.repository.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/operating")
public class UserOperatingController {

    private final UserService userService;
    private final BillboardService billboardService;
    private final GroupBillboardService groupService;
    private final LocationService locationService;

    @Autowired
    public UserOperatingController(UserService userService,
                                   BillboardService billboardService,
                                   GroupBillboardService groupService,
                                   LocationService locationService) {
        this.userService = userService;
        this.billboardService = billboardService;
        this.groupService = groupService;
        this.locationService = locationService;
    }

    @GetMapping(value = "/billboards")
    public ResponseEntity<List<Billboard>> getBillboardsByLocation(@RequestBody List<String> locations) {
        List<Billboard> billboards = billboardService.getListBillboardsByLocations(locations);
        if (billboards.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(billboards, HttpStatus.OK);
    }

    //p
    @GetMapping(value = "/locations")
    public ResponseEntity<List<Location>> getAllLocations() {
        List<Location> locations = locationService.getLocation();
        if (locations.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(locations, HttpStatus.OK);
    }

    @PatchMapping(value = "/buy/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Billboard> updateUserId(
            @PathVariable("id") Long id,
            @RequestBody Long userId,
            @RequestBody List<Ad> ads) {
        Billboard billboard = billboardService.getBillboardById(id);
        if (billboard == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        billboard.setUserId(userId);
        billboard.setAds(ads);
        billboardService.updateBillboard(billboard);
        return new ResponseEntity<>(billboard, HttpStatus.OK);
    }
}
