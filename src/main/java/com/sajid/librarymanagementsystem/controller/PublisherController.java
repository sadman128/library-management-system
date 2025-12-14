package com.sajid.librarymanagementsystem.controller;

import com.sajid.librarymanagementsystem.model.Publisher;
import com.sajid.librarymanagementsystem.service.PublisherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PublisherController {

    private final PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping("publisher")
    public String publishers(Model model) {

        Publisher publisher = new Publisher();
        List<Publisher> publishers = publisherService.getAllPublishers();

        model.addAttribute("publishers", publishers);
        model.addAttribute("blank_publisher", publisher);

        return "publisher";
    }


    @PostMapping("newpublisher")
    public String newPublisher(@ModelAttribute Publisher publisher, Model model) {
        publisherService.addPublisher(publisher);
        return "redirect:/publisher?addedpublisher";
    }


    @GetMapping("/deletepublisher/{id}")
    public String deletePublisher(@PathVariable int id, Model model) {
        publisherService.deletePublisher(id);
        return "redirect:/publisher?deletedpublisher";
    }


    @PostMapping("updatepublisher")
    public String updatePublisher(@ModelAttribute Publisher publisher, Model model) {
        publisherService.updatePublisher(publisher);
        return "redirect:/publisher?updatedpublisher";
    }

}
