package org.example.vladsin.adverboard.web.controller.view;

import org.example.vladsin.adverboard.model.Ad;
import org.example.vladsin.adverboard.model.Billboard;
import org.example.vladsin.adverboard.model.Location;
import org.example.vladsin.adverboard.service.repository.BillboardRepositoryService;
import org.example.vladsin.adverboard.service.repository.LocationRepositoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/web")
public class WebController {

    private final BillboardRepositoryService billboardRepositoryService;
    private final LocationRepositoryService locationRepositoryService;

    public WebController(BillboardRepositoryService billboardRepositoryService, LocationRepositoryService locationRepositoryService) {
        this.billboardRepositoryService = billboardRepositoryService;
        this.locationRepositoryService = locationRepositoryService;
    }

    @GetMapping(value = "/locations")
    public String getAllLocations(HttpServletRequest request) {
        List<Location> locations = locationRepositoryService.getLocation();
        request.setAttribute("locations", locations);
        return "locations";
    }

    @PostMapping(value = "/stream")
    public String getAds(HttpServletRequest request) {
        Billboard billboard = billboardRepositoryService.getBillboardByLocation(request.getParameter("location"));
        List<Ad> ads = billboard.getAds();
        request.setAttribute("ads", ads);
        return "stream";
    }
}
