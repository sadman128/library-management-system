package com.sajid.librarymanagementsystem.controller;

import com.sajid.librarymanagementsystem.model.Allotment;
import com.sajid.librarymanagementsystem.model.Book;
import com.sajid.librarymanagementsystem.model.Student;
import com.sajid.librarymanagementsystem.service.AllotmentService;
import com.sajid.librarymanagementsystem.service.BookService;
import com.sajid.librarymanagementsystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class AllotmentController {
    private final AllotmentService allotmentService;
    private final StudentService studentService;
    private final BookService bookService;

    public AllotmentController(AllotmentService allotmentService, StudentService studentService, BookService bookService) {
        this.allotmentService = allotmentService;
        this.studentService = studentService;
        this.bookService = bookService;
    }


    @GetMapping("allotment")
    public String allotment(Model model) {
        List<Allotment> allotments = allotmentService.getAllAllotments();
        List<Student> students = studentService.getAllStudents();
        List<Book> books = bookService.getAllBooks();

                model.addAttribute("allotments", allotments);
        model.addAttribute("students", students);
        model.addAttribute("books", books);

        Allotment allotment = new Allotment();
        allotment.setIssuedate(LocalDate.now().toString());
        allotment.setReturndate(LocalDate.now().toString());
        allotment.setUnit(1);

        model.addAttribute("blank_allotment", allotment);
        return "allotment";
    }

    @PostMapping("issuebook")
    public String issueBook(@ModelAttribute  Allotment allotment, Model model) {
        IO.println("AllotmentController::issueBook" +  allotment.getStudentcode());

        allotmentService.issueBook(allotment);
        return "redirect:/allotment?bookissue";
    }
    @PostMapping("updatestatus")
    public String updateStatus(@ModelAttribute  Allotment allotment, Model model) {

        allotmentService.statusUpdate(allotment);
        return "redirect:/allotment?statusupdate";
    }

    @GetMapping("/allotment/transcript/{id}")
    public ResponseEntity<byte[]> generateAllotmentPDF(@PathVariable int id) {
        return allotmentService.getTranscript(id);
    }

}
