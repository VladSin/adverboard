package org.example.vladsin.adverboard.web.controller.rest;

import org.example.vladsin.adverboard.model.Ad;
import org.example.vladsin.adverboard.model.Billboard;
import org.example.vladsin.adverboard.service.repository.BillboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/billboard")
public class BillboardRestController {

    private final BillboardService billboardService;
    @Autowired
    public BillboardRestController(BillboardService billboardService) {
        this.billboardService = billboardService;
    }

    @PostMapping(value = "/buy")
    @ResponseStatus(HttpStatus.CREATED)
    public Long addBillboard(@RequestBody Billboard billboard){
        Billboard newBillboard = billboardService.saveBillboard(billboard);
        return newBillboard.getId();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Billboard> getBillboard(@PathVariable("id") Long id) {
        Billboard billboard = billboardService.getBillboardById(id);

        if (billboard == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(billboard, HttpStatus.OK);
    }

    @GetMapping(value = "/{location}")
    public ResponseEntity<Billboard> getBillboard(@PathVariable("location") String location) {
        Billboard billboard = billboardService.getBillboardByLocation(location);

        if (billboard == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(billboard, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<List<Billboard>> getBillboards() {
        List<Billboard> billboards = billboardService.getBillboards();

        if (billboards.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(billboards, HttpStatus.OK);
    }

    @GetMapping(value = "/user/{userId}")
    public ResponseEntity<List<Billboard>> getBillboardsByUserId(@PathVariable("userId") Long userId) {
        List<Billboard> billboards = billboardService.getBillboardsByUserId(userId);

        if (billboards.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(billboards, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Billboard> updateBillboard(
            @PathVariable("id") Long id,
            @RequestBody Billboard newBillboard){
        Billboard billboard = billboardService.getBillboardById(id);

        if(billboard == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        billboard.setLocation(newBillboard.getLocation());
        billboard.setPrice(newBillboard.getPrice());
        billboard.setUserId(newBillboard.getUserId());
        billboard.setAds(newBillboard.getAds());
        billboardService.updateBillboard(billboard);
        return new ResponseEntity<>(newBillboard, HttpStatus.OK);
    }

    @PatchMapping(value = "/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Billboard> updateUserId(
            @PathVariable("id") Long id,
            @RequestBody Long userId) {
        Billboard billboard = billboardService.getBillboardById(id);
        if (billboard == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        billboard.setUserId(userId);
        billboardService.updateBillboard(billboard);
        return new ResponseEntity<>(billboard, HttpStatus.OK);
    }

    @PatchMapping(value = "/ads/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Billboard> updateAds(
            @PathVariable("id") Long id,
            @RequestBody List<Ad> ads) {
        Billboard billboard = billboardService.getBillboardById(id);
        if (billboard == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        billboard.setAds(ads);
        billboardService.updateBillboard(billboard);
        return new ResponseEntity<>(billboard, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteBillboard(@PathVariable("id") Long id){
        billboardService.deleteBillboard(id);
        return "OK";
    }
}
