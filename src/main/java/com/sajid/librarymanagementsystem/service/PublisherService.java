package com.sajid.librarymanagementsystem.service;

import com.sajid.librarymanagementsystem.model.Publisher;
import com.sajid.librarymanagementsystem.repository.PublisherRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PublisherService {
    private final PublisherRepository publisherRepository;

    private static List<Publisher> publishers = new ArrayList<>();

    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public List<Publisher> getAllPublishers() {
        publishers = publisherRepository.findAll();
        return publishers;
    }

    public void addPublisher(Publisher publisher) {
        publisherRepository.save(publisher);
    }


    public void deletePublisher(int id) {
        publisherRepository.deleteById(id);
    }

    public void updatePublisher(Publisher publisher) {
        publisherRepository.save(publisher);
    }

}
