package com.sajid.librarymanagementsystem.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sajid.librarymanagementsystem.dto.DashboardDto;
import com.sajid.librarymanagementsystem.repository.*;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {
    private final StudentRepository studentRepository;
    private final BookRepository bookRepository;
    private final AllotmentRepository allotmentRepository;
    private final BookOrderRepository bookOrderRepository;
    private final PublisherRepository publisherRepository;
    private final VendorRepository vendorRepository;

    public DashboardService(StudentRepository studentRepository, BookRepository bookRepository, AllotmentRepository allotmentRepository, BookOrderRepository bookOrderRepository, PublisherRepository publisherRepository, VendorRepository vendorRepository) {
        this.studentRepository = studentRepository;
        this.bookRepository = bookRepository;
        this.allotmentRepository = allotmentRepository;
        this.bookOrderRepository = bookOrderRepository;
        this.publisherRepository = publisherRepository;
        this.vendorRepository = vendorRepository;
    }

    public DashboardDto getDashboardStats() {
            int studentCount = Math.toIntExact(studentRepository.count());

            int bookCount = Math.toIntExact(bookRepository.count());

            int bookStockCount = bookRepository.totalBookStock();

            int vendorCount = Math.toIntExact(vendorRepository.count());

            int publisherCount = Math.toIntExact(publisherRepository.count());

            int allotmentCount = Math.toIntExact(allotmentRepository.count());

            double totalEarning = allotmentRepository.totalEarning();

            return new DashboardDto(
                    String.valueOf(studentCount),
                    String.valueOf(bookCount),
                    String.valueOf(bookStockCount),
                    String.valueOf(vendorCount),
                    String.valueOf(publisherCount),
                    String.valueOf(allotmentCount),
                    String.valueOf(totalEarning)
            );

    }
}
