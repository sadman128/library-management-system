package com.sajid.librarymanagementsystem.controller;

import com.sajid.librarymanagementsystem.model.Allotment;
import com.sajid.librarymanagementsystem.model.Book;
import com.sajid.librarymanagementsystem.model.Student;
import com.sajid.librarymanagementsystem.service.AllotmentService;
import com.sajid.librarymanagementsystem.service.BookService;
import com.sajid.librarymanagementsystem.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class StudentController {

    public final StudentService studentService;
    public final AllotmentService allotmentService;
    public final BookService bookService;

    public StudentController(StudentService studentService, AllotmentService allotmentService, BookService bookService) {
        this.studentService = studentService;
        this.allotmentService = allotmentService;
        this.bookService = bookService;
    }

    @GetMapping("student")
    public String students(Model model) {

        Student student = new Student();
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        model.addAttribute("blank_student", student);

        Map<String, List<Allotment>> allottedBooksMap =
                allotmentService.getAllottedBooksMap();
        model.addAttribute("allottedBooksMap", allottedBooksMap);

        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);

        Allotment allotment = new Allotment();
        allotment.setIssuedate(LocalDate.now().toString());
        allotment.setReturndate(LocalDate.now().toString());
        allotment.setUnit(1);
        model.addAttribute("blank_allotment", allotment);
        return "student";
    }

    @PostMapping("newstudent")
    public String newStudent(@ModelAttribute Student student, Model model) {
        studentService.addStudent(student);
        return "redirect:/student?addedstudent";
    }

    @GetMapping("/deletestudent/{id}")
    public String deleteStudent(@PathVariable int id, Model model) {
        studentService.deleteStudent(id);
        return "redirect:/student?deletedstudent";
    }

    @PostMapping("updatestudent")
    public String updateStudent(@ModelAttribute Student student, Model model) {
        studentService.updateStudent(student);
        return "redirect:/student?updatedstudent";
    }

    @PostMapping("studentissuebook")
    public String issueBook(@ModelAttribute  Allotment allotment, Model model) {
        IO.println("AllotmentController::issueBook" +  allotment.getStudentcode());

        allotmentService.issueBook(allotment);
        return "redirect:/student?bookissue";
    }

}
