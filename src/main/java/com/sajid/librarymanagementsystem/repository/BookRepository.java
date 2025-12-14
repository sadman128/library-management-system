package com.sajid.librarymanagementsystem.repository;

import com.sajid.librarymanagementsystem.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query("SELECT COALESCE(SUM(b.quantity), 0) FROM Book b")
    int totalBookStock();
}
