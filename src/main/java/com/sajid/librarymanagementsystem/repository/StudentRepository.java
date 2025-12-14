package com.sajid.librarymanagementsystem.repository;

import com.sajid.librarymanagementsystem.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    Student findByCode(String code);
}
