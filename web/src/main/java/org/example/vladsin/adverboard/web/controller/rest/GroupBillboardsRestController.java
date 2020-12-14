package org.example.vladsin.adverboard.web.controller.rest;

import org.example.vladsin.adverboard.model.Billboard;
import org.example.vladsin.adverboard.model.GroupBillboards;
import org.example.vladsin.adverboard.service.repository.GroupBillboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/group")
public class GroupBillboardsRestController {

    private final GroupBillboardService groupBillboardService;

    @Autowired
    public GroupBillboardsRestController(GroupBillboardService groupBillboardService) {
        this.groupBillboardService = groupBillboardService;
    }

    @PostMapping(value = "/create/{userId}/{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public Long addGroupBillboards(@PathVariable("userId") Long userId,
                                   @PathVariable("name") String name,
                                   @RequestBody List<Billboard> billboards) {
        List<Billboard> billboardList = new ArrayList<>();
        for (Billboard b : billboards) {
            billboardList.add(new Billboard(null, b.getLocation(), b.getPrice(), userId, null));
        }
        GroupBillboards newGroup = groupBillboardService.saveGroup(new GroupBillboards(null, name, userId, billboardList));
        return newGroup.getId();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<GroupBillboards> getGroupBillboards(@PathVariable("id") Long id) {
        GroupBillboards group = groupBillboardService.getGroupById(id);

        if (group == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(group, HttpStatus.OK);
    }

    @GetMapping(value = "/user/{userId}")
    public ResponseEntity<GroupBillboards> getGroupByUserId(@PathVariable("userId") Long userId) {
        GroupBillboards group = groupBillboardService.getGroupByUserId(userId);

        if (group == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(group, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<GroupBillboards> updateBillboard(
            @PathVariable("id") Long id,
            @RequestBody GroupBillboards newGroup) {
        GroupBillboards group = groupBillboardService.getGroupById(id);

        if (group == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        group.setGroupName(newGroup.getGroupName());
        group.setUserId(newGroup.getUserId());
        group.setBillboards(newGroup.getBillboards());
        groupBillboardService.updateGroup(group);
        return new ResponseEntity<>(newGroup, HttpStatus.OK);
    }

    @GetMapping(value = "/groups/{userId}")
    public ResponseEntity<List<GroupBillboards>> getBillboardsByUserId(@PathVariable("userId") Long userId) {
        List<GroupBillboards> groupBillboards = groupBillboardService.getGroupsByUserId(userId);

        if (groupBillboards.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(groupBillboards, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteGroupBillboards(@PathVariable("id") Long id) {
        groupBillboardService.deleteGroup(id);
        return "OK";
    }
}
