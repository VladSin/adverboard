package org.example.vladsin.adverboard.web.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.vladsin.adverboard.model.Ad;
import org.example.vladsin.adverboard.model.Billboard;
import org.example.vladsin.adverboard.model.GroupBillboards;
import org.example.vladsin.adverboard.model.Location;
import org.example.vladsin.adverboard.model.controller.BillboardJson;
import org.example.vladsin.adverboard.model.controller.GroupBillboardsJson;
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
import java.util.stream.Collectors;

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

    // Теперь отдаю не List<Billboard>, а List<BillboardJson>
    @PostMapping(value = "/billboards")
    public ResponseEntity<List<BillboardJson>> getBillboardsByLocation(@RequestBody String jsonString) throws IOException {
        final ObjectMapper objectMapper = new ObjectMapper();
        List<String> locations = objectMapper.readValue(jsonString, new TypeReference<List<String>>(){});

        List<Billboard> billboards = billboardRepositoryService.getListBillboardsByLocations(locations);
        if (billboards.isEmpty()){
            log.info("NO_CONTENT logged at {}", LocalDateTime.now());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<BillboardJson> billboardJsons = new ArrayList<>();
        for (Billboard b: billboards) {
            List<Ad> ads = adRepositoryService.getAdByBillboardId(b.getId());
            billboardJsons.add(new BillboardJson(b.getLocation(), null, null,
                    b.getId(),
                    b.getUserId(),
                    b.getPrice(),
                    ads.stream().map(Ad::getLink).collect(Collectors.toList()), null));
        }
        return new ResponseEntity<>(billboardJsons, HttpStatus.OK);
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

    // Теперь отдаю не Billboard.class, а BillboardJson.class
    @PatchMapping(value = "/buy/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BillboardJson> buyBillboard(
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

        List<Ad> ads = adRepositoryService.getAdByBillboardId(billboard.getId());
        BillboardJson billboardJson = new BillboardJson(billboard.getLocation(), null, null,
                billboard.getId(),
                billboard.getUserId(),
                billboard.getPrice(),
                ads.stream().map(Ad::getLink).collect(Collectors.toList()), null);

        return new ResponseEntity<>(billboardJson, HttpStatus.OK);
    }

    // Теперь отдаю не Billboard.class, а BillboardJson.class
    @PatchMapping(value = "/set/billboard/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BillboardJson> setBillboardMedia(
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

        BillboardJson billboardJson = new BillboardJson(billboard.getLocation(), null, null,
                billboard.getId(),
                billboard.getUserId(),
                billboard.getPrice(),
                ads.stream().map(Ad::getLink).collect(Collectors.toList()), null);
        return new ResponseEntity<>(billboardJson, HttpStatus.OK);
    }

    // Теперь отдаю не GroupBillboards.class, а GroupBillboardsJson.class
    @PatchMapping(value = "/set/group/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GroupBillboardsJson> setGroupMedia(
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

        List<BillboardJson> billboardJsons = new ArrayList<>();
        for (Billboard b: billboards) {
            List<Ad> ads = adRepositoryService.getAdByBillboardId(b.getId());
            billboardJsons.add(new BillboardJson(b.getLocation(), null, null,
                    b.getId(),
                    b.getUserId(),
                    b.getPrice(),
                    ads.stream().map(Ad::getLink).collect(Collectors.toList()), null));
        }

        GroupBillboardsJson groupJson = new GroupBillboardsJson(groupBillboards.getId(), null, null,
                groupBillboards.getGroupName(), groupBillboards.getUserId(), billboardJsons, links);
        log.info("group billboards updated {} logged at {}", groupBillboards.getGroupName(), LocalDateTime.now());
        return new ResponseEntity<>(groupJson, HttpStatus.OK);
    }
}