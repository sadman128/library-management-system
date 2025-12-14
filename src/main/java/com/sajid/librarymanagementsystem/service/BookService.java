package com.sajid.librarymanagementsystem.service;

import com.sajid.librarymanagementsystem.model.Book;
import com.sajid.librarymanagementsystem.model.BookOrder;
import com.sajid.librarymanagementsystem.repository.BookOrderRepository;
import com.sajid.librarymanagementsystem.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final BookOrderRepository bookOrderRepository;
    public static List<Book> books = new ArrayList<>();
    public static List<BookOrder> bookOrders = new ArrayList<>();

    public BookService(BookRepository bookRepository, BookOrderRepository bookOrderRepository) {
        this.bookRepository = bookRepository;
        this.bookOrderRepository = bookOrderRepository;
    }


    public List<Book> getAllBooks() {
        books = bookRepository.findAll();
        return books;
    }


    public void addBook(Book book) {
        bookRepository.save(book);
    }

    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }

    public void updateBook(Book book) {
        bookRepository.save(book);
    }

    public void orderBook(BookOrder bookOrder) {
        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String sqlDate = sdf.format(today);
        bookOrder.setDate(sqlDate);

        bookOrderRepository.save(bookOrder);

        Book book = bookRepository.findById(bookOrder.getBookId())
                .orElseThrow(() -> new RuntimeException("Book not found"));

        book.setQuantity(book.getQuantity() + bookOrder.getUnit());
        bookRepository.save(book);
    }


    public Map<Integer, List<BookOrder>> getOrderHistoryMap() {

        Map<Integer, List<BookOrder>> orderHistoryMap = new HashMap<>();

        List<BookOrder> orders = bookOrderRepository.findAll();

        for (BookOrder order : orders) {
            int bookId = order.getBookId();
            orderHistoryMap
                    .computeIfAbsent(bookId, k -> new ArrayList<>())
                    .add(order);
        }

        return orderHistoryMap;
    }



}
