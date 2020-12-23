package org.example.vladsin.adverboard.web.controller.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.vladsin.adverboard.model.Ad;
import org.example.vladsin.adverboard.model.Billboard;
import org.example.vladsin.adverboard.model.GroupBillboards;
import org.example.vladsin.adverboard.model.controller.BillboardJson;
import org.example.vladsin.adverboard.model.controller.GroupBillboardsJson;
import org.example.vladsin.adverboard.service.repository.AdRepositoryService;
import org.example.vladsin.adverboard.service.repository.BillboardRepositoryService;
import org.example.vladsin.adverboard.service.repository.GroupBillboardRepositoryService;
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
@RequestMapping("/group")
public class GroupBillboardsRestController {

    private static final Logger log = LoggerFactory.getLogger(GroupBillboardsRestController.class);

    private final GroupBillboardRepositoryService groupBillboardRepositoryService;
    private final BillboardRepositoryService billboardRepositoryService;
    private final AdRepositoryService adRepositoryService;

    @Autowired
    public GroupBillboardsRestController(GroupBillboardRepositoryService groupBillboardRepositoryService, BillboardRepositoryService billboardRepositoryService, AdRepositoryService adRepositoryService) {
        this.groupBillboardRepositoryService = groupBillboardRepositoryService;
        this.billboardRepositoryService = billboardRepositoryService;
        this.adRepositoryService = adRepositoryService;
    }

    @PostMapping(value = "/create/{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public Long addGroupBillboards(@PathVariable("name") String name,
                                   @RequestBody String jsonString) throws IOException {
        final ObjectMapper objectMapper = new ObjectMapper();
        List<BillboardJson> billboards = objectMapper.readValue(jsonString, new TypeReference<List<BillboardJson>>(){});

        GroupBillboards newGroup = groupBillboardRepositoryService.saveGroup(new GroupBillboards(null, name, billboards.get(1).getUserId(), null));
        for (BillboardJson b : billboards) {
            billboardRepositoryService.updateBillboard(new Billboard(b.getId(), b.getLocation(), b.getPrice(), b.getUserId(), newGroup.getId(), null));
            if(b.getAds() != null){
                for (String l: b.getAds()) {
                    adRepositoryService.saveAd(new Ad(null, l, b.getId()));
                }
            }
        }
        log.info("group created {} logged at {}", newGroup.getId(), LocalDateTime.now());
        return newGroup.getId();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<GroupBillboardsJson> getGroupBillboards(@PathVariable("id") Long id) {
        GroupBillboards group = groupBillboardRepositoryService.getGroupById(id);
        if (group == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        List<Billboard> billboards = billboardRepositoryService.getBillboardsByGroupId(id);

        List<BillboardJson> billboardJsons = new ArrayList<>();
        for (Billboard b: billboards) {
            List<Ad> ads = adRepositoryService.getAdByBillboardId(b.getId());
            billboardJsons.add(new BillboardJson(b.getLocation(), null, null,
                    b.getId(),
                    b.getUserId(),
                    b.getPrice(),
                    ads.stream().map(Ad::getLink).collect(Collectors.toList()), null));
        }

        List<String> links = new ArrayList<>();
        try {
            links = billboardJsons.get(0).getAds();
        } catch (RuntimeException e){
            log.info("billboards do not have advertising from the group {} logged at {}", id, LocalDateTime.now());
        }

        GroupBillboardsJson groupJson = new GroupBillboardsJson(group.getId(), null, null,
                group.getGroupName(), group.getUserId(), billboardJsons, links);
        return new ResponseEntity<>(groupJson, HttpStatus.OK);
    }

    @GetMapping(value = "/get/{userId}")
    public ResponseEntity<List<GroupBillboardsJson>> getGroupsBillboardsByUserId(@PathVariable("userId") Long userId) {
        List<GroupBillboards> group = groupBillboardRepositoryService.getGroupsByUserId(userId);
        if (group.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        List<GroupBillboardsJson> groupBillboardsJsons = new ArrayList<>();
        for (GroupBillboards g: group) {

            List<BillboardJson> billboardJsons = new ArrayList<>();
            for (Billboard b: billboardRepositoryService.getBillboardsByGroupId(g.getId())) {
                List<Ad> ads = adRepositoryService.getAdByBillboardId(b.getId());
                billboardJsons.add(new BillboardJson(b.getLocation(), null, null,
                        b.getId(),
                        b.getUserId(),
                        b.getPrice(),
                        ads.stream().map(Ad::getLink).collect(Collectors.toList()), null));
            }

            List<String> links = new ArrayList<>();
            try {
                links = billboardJsons.get(0).getAds();
            } catch (RuntimeException e){
                log.info("billboards do not have advertising from the user {} logged at {}", userId, LocalDateTime.now());
            }
            groupBillboardsJsons.add(new GroupBillboardsJson(g.getId(), null, null,
                    g.getGroupName(), g.getUserId(), billboardJsons, links));
        }

        return new ResponseEntity<>(groupBillboardsJsons, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<GroupBillboards> updateBillboard(
            @PathVariable("id") Long id,
            @RequestBody GroupBillboards newGroup) {
        GroupBillboards group = groupBillboardRepositoryService.getGroupById(id);

        if (group == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        group.setGroupName(newGroup.getGroupName());
        group.setUserId(newGroup.getUserId());
        group.setBillboards(newGroup.getBillboards());
        groupBillboardRepositoryService.updateGroup(group);
        log.info("group updated {} logged at {}", id, LocalDateTime.now());
        return new ResponseEntity<>(newGroup, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteGroupBillboards(@PathVariable("id") Long id) {
        groupBillboardRepositoryService.deleteGroup(id);
        log.info("group deleted {} logged at {}", id, LocalDateTime.now());
        return "OK";
    }
}
