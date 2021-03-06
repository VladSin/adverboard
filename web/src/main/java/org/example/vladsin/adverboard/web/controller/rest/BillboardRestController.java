package org.example.vladsin.adverboard.web.controller.rest;

import org.example.vladsin.adverboard.model.Ad;
import org.example.vladsin.adverboard.model.Billboard;
import org.example.vladsin.adverboard.model.controller.BillboardJson;
import org.example.vladsin.adverboard.service.repository.AdRepositoryService;
import org.example.vladsin.adverboard.service.repository.BillboardRepositoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/billboard")
public class BillboardRestController {

    private static final Logger log = LoggerFactory.getLogger(BillboardRestController.class);

    private final BillboardRepositoryService billboardRepositoryService;
    private final AdRepositoryService adRepositoryService;

    @Autowired
    public BillboardRestController(BillboardRepositoryService billboardRepositoryService, AdRepositoryService adRepositoryService) {
        this.billboardRepositoryService = billboardRepositoryService;
        this.adRepositoryService = adRepositoryService;
    }

    @PostMapping(value = "")
    @ResponseStatus(HttpStatus.CREATED)
    public Long addBillboard(@RequestBody Billboard billboard){
        Billboard newBillboard = billboardRepositoryService.saveBillboard(billboard);
        log.info("billboard created {} logged at {}", newBillboard.getId(), LocalDateTime.now());
        return newBillboard.getId();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Billboard> getBillboard(@PathVariable("id") Long id) {
        Billboard billboard = billboardRepositoryService.getBillboardById(id);

        if (billboard == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(billboard, HttpStatus.OK);
    }

    @GetMapping(value = "/{location}")
    public ResponseEntity<Billboard> getBillboard(@PathVariable("location") String location) {
        Billboard billboard = billboardRepositoryService.getBillboardByLocation(location);

        if (billboard == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(billboard, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<List<Billboard>> getBillboards() {
        List<Billboard> billboards = billboardRepositoryService.getBillboards();

        if (billboards.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(billboards, HttpStatus.OK);
    }

    @GetMapping(value = "/user/{userId}")
    public ResponseEntity<List<BillboardJson>> getBillboardsByUserId(@PathVariable("userId") Long userId) {
        List<Billboard> billboards = billboardRepositoryService.getBillboardsByUserId(userId);

        if (billboards.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        List<BillboardJson> billboardJsons = new ArrayList<>();
        for (Billboard b: billboards) {
            List<Ad> ads = adRepositoryService.getAdByBillboardId(b.getId());

            String verification= "unverified";
            int goodAds = 0;
            for (Ad a: ads) {
                if(a != null && a.getVerification().equals("verified"))
                    goodAds++;
            }
            if (ads.size() == goodAds){
                verification = "verified";
            }

            billboardJsons.add(new BillboardJson(b.getLocation(), null, null,
                    b.getId(),
                    b.getUserId(),
                    b.getPrice(),
                    ads.stream().map(Ad::getLink).collect(Collectors.toList()), verification));
        }
        return new ResponseEntity<>(billboardJsons, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Billboard> updateBillboard(
            @PathVariable("id") Long id,
            @RequestBody Billboard newBillboard){
        Billboard billboard = billboardRepositoryService.getBillboardById(id);

        if(billboard == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        billboard.setLocation(newBillboard.getLocation());
        billboard.setPrice(newBillboard.getPrice());
        billboard.setUserId(newBillboard.getUserId());
        billboard.setAds(newBillboard.getAds());
        billboardRepositoryService.updateBillboard(billboard);
        log.info("billboard updated {} logged at {}", newBillboard.getId(), LocalDateTime.now());
        return new ResponseEntity<>(newBillboard, HttpStatus.OK);
    }

    @PatchMapping(value = "/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Billboard> updateUserId(
            @PathVariable("id") Long id,
            @RequestBody Long userId) {
        Billboard billboard = billboardRepositoryService.getBillboardById(id);
        if (billboard == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        billboard.setUserId(userId);
        billboardRepositoryService.updateBillboard(billboard);
        log.info("billboard updated {} logged at {}", id, LocalDateTime.now());
        return new ResponseEntity<>(billboard, HttpStatus.OK);
    }

    @PatchMapping(value = "/ads/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Billboard> updateAds(
            @PathVariable("id") Long id,
            @RequestBody List<Ad> ads) {
        Billboard billboard = billboardRepositoryService.getBillboardById(id);
        if (billboard == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        billboard.setAds(ads);
        billboardRepositoryService.updateBillboard(billboard);
        log.info("billboard updated {} logged at {}", id, LocalDateTime.now());
        return new ResponseEntity<>(billboard, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteBillboard(@PathVariable("id") Long id){
        billboardRepositoryService.deleteBillboard(id);
        log.info("billboard deleted {} logged at {}", id, LocalDateTime.now());
        return "OK";
    }
}
