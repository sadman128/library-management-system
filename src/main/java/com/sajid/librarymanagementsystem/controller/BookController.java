package com.sajid.librarymanagementsystem.controller;

import com.sajid.librarymanagementsystem.model.Book;
import com.sajid.librarymanagementsystem.model.BookOrder;
import com.sajid.librarymanagementsystem.model.Publisher;
import com.sajid.librarymanagementsystem.model.Vendor;
import com.sajid.librarymanagementsystem.service.BookService;
import com.sajid.librarymanagementsystem.service.PublisherService;
import com.sajid.librarymanagementsystem.service.VendorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class BookController {
    private final BookService bookService;
    private final VendorService vendorService;
    private final PublisherService publisherService;

    public BookController(BookService bookService, VendorService vendorService, PublisherService publisherService) {
        this.bookService = bookService;
        this.vendorService = vendorService;
        this.publisherService = publisherService;
    }

    @GetMapping("book")
    public String books(Model model) {

        List<Book> books = bookService.getAllBooks();
        List<Vendor> vendors = vendorService.getAllVendors();
        List<Publisher> publishers = publisherService.getAllPublishers();
        Map<Integer, List<BookOrder>> orderHistoryMap = bookService.getOrderHistoryMap();
        model.addAttribute("blank_book",  new Book());
        model.addAttribute("books", books);
        model.addAttribute("vendors", vendors);
        model.addAttribute("publishers", publishers);
        model.addAttribute("bookOrder", new BookOrder());
        model.addAttribute("orderHistoryMap", orderHistoryMap);

        return "book";
    }


    @PostMapping("/newbook")
    public String newBook(@ModelAttribute Book book, Model model) {
        bookService.addBook(book);
        return "redirect:/book?addedbook";
    }

    @GetMapping("/deletebook/{id}")
    public String deleteBook(@PathVariable int id, Model model) {
        bookService.deleteBook(id);
        return "redirect:/book?deletedbook";
    }

    @PostMapping("/updatebook")
    public String updateBook(@ModelAttribute Book book, Model model) {
        bookService.updateBook(book);
        return "redirect:/book?updatedbook";
    }

    @PostMapping("orderbook")
    public String orderBook(@ModelAttribute BookOrder bookOrder, Model model) {
        bookService.orderBook(bookOrder);
        return "redirect:/book?bookordered";
    }

}

