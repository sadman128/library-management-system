package com.sajid.librarymanagementsystem.controller;

import com.sajid.librarymanagementsystem.dto.DashboardDto;
import com.sajid.librarymanagementsystem.service.DashboardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class DashboardController {
    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }


    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        DashboardDto stats = dashboardService.getDashboardStats(); // Your method to fetch counts
        model.addAttribute("stats", stats);
        return "dashboard";
    }

}