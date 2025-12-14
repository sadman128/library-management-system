package com.sajid.librarymanagementsystem.service;

import com.sajid.librarymanagementsystem.model.Vendor;
import com.sajid.librarymanagementsystem.repository.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VendorService {
    private final VendorRepository vendorRepository;
    private static List<Vendor> vendors = new ArrayList<>();

    public VendorService(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    public List<Vendor> getAllVendors() {
        vendors = vendorRepository.findAll();
        return vendors;
    }

    public void addVendor(Vendor vendor) {
        vendorRepository.save(vendor);
    }

    public void deleteVendor(int id) {
        vendorRepository.deleteById(id);
    }

    public void updateVendor(Vendor vendor) {
        vendorRepository.save(vendor);
    }
}
