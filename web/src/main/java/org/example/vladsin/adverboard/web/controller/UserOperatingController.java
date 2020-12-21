package org.example.vladsin.adverboard.web.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.vladsin.adverboard.model.Ad;
import org.example.vladsin.adverboard.model.Billboard;
import org.example.vladsin.adverboard.model.GroupBillboards;
import org.example.vladsin.adverboard.model.Location;
import org.example.vladsin.adverboard.service.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/operating")
public class UserOperatingController {

    private static final Logger log = LoggerFactory.getLogger(UserOperatingController.class);

    private final BillboardRepositoryService billboardRepositoryService;
    private final GroupBillboardRepositoryService groupService;
    private final LocationRepositoryService locationRepositoryService;
    private final AdRepositoryService adRepositoryService;

    @Autowired
    public UserOperatingController(BillboardRepositoryService billboardRepositoryService,
                                   GroupBillboardRepositoryService groupService,
                                   LocationRepositoryService locationRepositoryService,
                                   AdRepositoryService adRepositoryService) {
        this.billboardRepositoryService = billboardRepositoryService;
        this.groupService = groupService;
        this.locationRepositoryService = locationRepositoryService;
        this.adRepositoryService = adRepositoryService;
    }

    @PostMapping(value = "/billboards")
    public ResponseEntity<List<Billboard>> getBillboardsByLocation(@RequestBody String jsonString) throws IOException {
        final ObjectMapper objectMapper = new ObjectMapper();
        List<String> locations = objectMapper.readValue(jsonString, new TypeReference<List<String>>(){});

        List<Billboard> billboards = billboardRepositoryService.getListBillboardsByLocations(locations);
        if (billboards.isEmpty()){
            log.info("NO_CONTENT logged at {}", LocalDateTime.now());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(billboards, HttpStatus.OK);
    }
    
    @GetMapping(value = "/locations")
    public ResponseEntity<List<Location>> getAllLocations() {
        List<Location> locations = locationRepositoryService.getLocation();
        if (locations.isEmpty())
        {
            log.info("NO_CONTENT logged at {}", LocalDateTime.now());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(locations, HttpStatus.OK);
    }

    @PatchMapping(value = "/buy/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Billboard> buyBillboard(
            @PathVariable("id") Long id,
            @RequestBody Long userId) {
        Billboard billboard = billboardRepositoryService.getBillboardById(id);

        if (billboard == null){
            log.info("BAD REQUEST logged at {}", LocalDateTime.now());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        billboard.setUserId(userId);
        billboardRepositoryService.updateBillboard(billboard);
        log.info("billboard bought{} logged at {}", billboard.getId(), LocalDateTime.now());
        return new ResponseEntity<>(billboard, HttpStatus.OK);
    }

    @PatchMapping(value = "/set/billboard/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Billboard> setBillboardMedia(
            @PathVariable("id") Long id,
            @RequestBody String jsonString) throws IOException {
        Billboard billboard = billboardRepositoryService.getBillboardById(id);

        if (billboard == null){
            log.info("NO_CONTENT logged at {}", LocalDateTime.now());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        final ObjectMapper objectMapper = new ObjectMapper();
        List<String> links = objectMapper.readValue(jsonString, new TypeReference<List<String>>(){});

        List<Ad> ads = new ArrayList<>();
        for (String l: links) {
            ads.add(adRepositoryService.saveAd(new Ad(null, l, billboard.getId())));
        }

        billboard.setAds(ads);

        log.info("set ads on billboard {} logged at {}", billboard.getId(), LocalDateTime.now());
        return new ResponseEntity<>(billboard, HttpStatus.OK);
    }

    @PatchMapping(value = "/set/group/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GroupBillboards> setGroupMedia(
            @PathVariable("id") Long id,
            @RequestBody String jsonString) throws IOException {
        GroupBillboards groupBillboards = groupService.getGroupById(id);

        if (groupBillboards == null){
            log.info("NO_CONTENT logged at {}", LocalDateTime.now());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        final ObjectMapper objectMapper = new ObjectMapper();
        List<String> links = objectMapper.readValue(jsonString, new TypeReference<List<String>>(){});

        List<Billboard> billboards = billboardRepositoryService.getBillboardsByGroupId(id);
        for (Billboard b: billboards) {
            for (String l: links) {
                b.addAd(adRepositoryService.saveAd(new Ad(null, l, b.getId())));
            }
        }
        groupBillboards.setBillboards(billboards);

        log.info("group billboards updated {} logged at {}", groupBillboards.getGroupName(), LocalDateTime.now());
        return new ResponseEntity<>(groupBillboards, HttpStatus.OK);
    }
}
