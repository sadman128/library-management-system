package com.sajid.librarymanagementsystem.controller;

import com.sajid.librarymanagementsystem.model.Vendor;
import com.sajid.librarymanagementsystem.service.VendorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class VendorController {

    public final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping("vendor")
    public String vendors(Model model) {

        Vendor vendor = new Vendor();
        List<Vendor> vendors = vendorService.getAllVendors();

        model.addAttribute("vendors", vendors);
        model.addAttribute("blank_vendor", vendor);

        return "vendor";
    }

    @PostMapping("newvendor")
    public String newVendor(@ModelAttribute Vendor vendor, Model model) {
        vendorService.addVendor(vendor);
        return "redirect:/vendor?addedvendor";
    }

    @GetMapping("/deletevendor/{id}")
    public String deleteVendor(@PathVariable int id) {
        vendorService.deleteVendor(id);
        return "redirect:/vendor?deletedvendor";
    }

    @PostMapping("updatevendor")
    public String updateVendor(@ModelAttribute Vendor vendor) {
        vendorService.updateVendor(vendor);
        return "redirect:/vendor?updatedvendor";
    }
}
