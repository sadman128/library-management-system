package com.sajid.librarymanagementsystem.repository;

import com.sajid.librarymanagementsystem.model.Allotment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AllotmentRepository extends JpaRepository<Allotment, Integer> {
    @Query("SELECT COALESCE(SUM(a.fare), 0) FROM Allotment a")
    double totalEarning();
}
