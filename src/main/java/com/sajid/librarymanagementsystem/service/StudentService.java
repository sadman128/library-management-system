package com.sajid.librarymanagementsystem.service;

import com.sajid.librarymanagementsystem.model.Student;
import com.sajid.librarymanagementsystem.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private static List<Student> students = new ArrayList<>();

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents()  {
        students = studentRepository.findAll();
        return students;
    }

    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    public void deleteStudent(int id) {
        studentRepository.deleteById(id);
    }

    public void updateStudent(Student student) {
        studentRepository.save(student);
    }


}
