package com.sajid.librarymanagementsystem.service;

import com.sajid.librarymanagementsystem.model.Allotment;
import com.sajid.librarymanagementsystem.model.Book;
import com.sajid.librarymanagementsystem.model.Student;
import com.sajid.librarymanagementsystem.repository.AllotmentRepository;
import com.sajid.librarymanagementsystem.repository.BookRepository;
import com.sajid.librarymanagementsystem.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

@Service
public class AllotmentService {
    private final AllotmentRepository allotmentRepository;
    private final BookRepository bookRepository;
    private final StudentRepository studentRepository;

    public AllotmentService(AllotmentRepository allotmentRepository, BookRepository bookRepository, StudentRepository studentRepository) {
        this.allotmentRepository = allotmentRepository;
        this.bookRepository = bookRepository;
        this.studentRepository = studentRepository;
    }

    public List<Allotment> getAllAllotments() {
        List<Allotment> allotments = new ArrayList<>();
        allotments = allotmentRepository.findAll();
        return allotments;
    }

    public Map<String, List<Allotment>> getAllottedBooksMap() {

        Map<String, List<Allotment>> map = new HashMap<>();
        List<Allotment> allAllotments = getAllAllotments();

        for (Allotment a : allAllotments) {
            map.putIfAbsent(a.getStudentcode(), new ArrayList<>());
            map.get(a.getStudentcode()).add(a);
        }

        return map;
    }

    @Transactional
    public void issueBook(Allotment allotment) {

        Date issueDate = Date.valueOf(allotment.getIssuedate());
        Date returnDate = Date.valueOf(allotment.getReturndate());

        long days = (returnDate.getTime() - issueDate.getTime()) / (1000 * 60 * 60 * 24);
        double fare = 0;
        if (days > 7) {
            fare = (days - 7) * 20 * allotment.getUnit();
        }
        allotment.setFare(fare);
        if (allotment.getStatus() == null) {
            allotment.setStatus("borrowed");
        }

        Student student = studentRepository
                .findByCode(allotment.getStudentcode());

        if (student != null) {
            allotment.setStudentname(student.getName());
        } else {
            throw new RuntimeException("Student not found");
        }

        int bookId = Integer.parseInt(allotment.getBookid());
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
        allotment.setBookname(book.getBookName());
        allotmentRepository.save(allotment);

        book.setQuantity(book.getQuantity() - allotment.getUnit());
        bookRepository.save(book);
    }

    @Transactional
    public void statusUpdate(Allotment allotment) {
        Allotment existingAllotment = allotmentRepository.findById(allotment.getId()).orElseThrow(() -> new RuntimeException("Allotment not found"));
        existingAllotment.setStatus(allotment.getStatus());

        if (allotment.getStatus().equalsIgnoreCase("returned")) {

            int bookId = Integer.parseInt(existingAllotment.getBookid());

            Book book = bookRepository.findById(bookId)
                    .orElseThrow(() -> new RuntimeException("Book not found"));

            book.setQuantity(book.getQuantity() + existingAllotment.getUnit());
            bookRepository.save(book);

        }
    }




// Chatgpt------------------------------------------------------------------------------------------------------------------------------------------


// BLUE THEME COLORS (Darkened)

    private final BaseColor PRIMARY = new BaseColor(24, 82, 185);        // darker blue
    private static final BaseColor CARD_BG = BaseColor.WHITE;


// FONTS

    private static final Font titleFont =
            new Font(Font.FontFamily.HELVETICA, 22, Font.BOLD, BaseColor.BLACK);

    private static final Font sectionFont =
            new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD, BaseColor.BLACK);

    private static final Font boldLabelFont =
            new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLACK);

    private static final Font valueFont =
            new Font(Font.FontFamily.COURIER, 12, Font.BOLDITALIC, BaseColor.BLACK);

    private static final Font footerFont =
            new Font(Font.FontFamily.HELVETICA, 12, Font.BOLDITALIC, BaseColor.BLACK);


// BLUE BOXED INFO LINE

    private PdfPTable boxedLine(String label, String value) {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);

        Chunk labelChunk = new Chunk(label + ":  ", boldLabelFont);
        Chunk valueChunk = new Chunk(value, valueFont);

        Paragraph p = new Paragraph();
        p.add(labelChunk);
        p.add(valueChunk);

        PdfPCell cell = new PdfPCell(p);
        cell.setPadding(10);
        cell.setBackgroundColor(CARD_BG);
        cell.setBorderColor(PRIMARY);
        cell.setBorderWidth(1.3f);

        table.addCell(cell);
        return table;
    }


// SECTION HEADER WITH BLUE UNDERLINE


    private void addSection(Document doc, String titleText) throws Exception {
        Paragraph h = new Paragraph(titleText, sectionFont);
        h.setSpacingBefore(15);
        h.setSpacingAfter(5);
        doc.add(h);

        PdfPTable line = new PdfPTable(1);
        line.setWidthPercentage(100);

        PdfPCell c = new PdfPCell();
        c.setBorder(Rectangle.BOTTOM);
        c.setBorderColor(PRIMARY);
        c.setBorderWidth(2);
        c.setPadding(2);
        c.setBorderWidthTop(0);
        c.setBorderWidthLeft(0);
        c.setBorderWidthRight(0);

        line.addCell(c);
        doc.add(line);
        doc.add(new Paragraph("\n"));
    }



// MAIN METHOD – COMPLETE PDF GENERATION

    public ResponseEntity<byte[]> getTranscript(int allotId) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

            Allotment allotment = allotmentRepository.findById(allotId).orElseThrow(() -> new RuntimeException("Allotment not found"));

            int id = allotment.getId();
            String studentCode = allotment.getStudentcode();
            String bookId = allotment.getBookid();
            int unit = allotment.getUnit();
            double fare = allotment.getFare();
            String issueDate = allotment.getIssuedate();
            String returnDate = allotment.getReturndate();
            String status = allotment.getStatus();



            Student student = studentRepository.findByCode(studentCode);

            String sName  = student.getName();
            String sCode  = student.getCode();
            String sEmail = student.getEmail();
            String sPhone = student.getPhone();


            Book book = bookRepository.findById(Integer.valueOf(bookId)).orElseThrow(() -> new RuntimeException("Allotment not found"));

            String bName = book.getBookName();
            String author = book.getAuthor();
            String publisher = book.getPublisherName();
            double price = book.getPrice();




            // ---------------------------------------------------------
            // PDF CREATION
            // ---------------------------------------------------------
            Document document = new Document(PageSize.A4, 40, 40, 60, 60);
            PdfWriter writer = PdfWriter.getInstance(document, baos);
            document.open();


            // ---------------------------------------------------------
            // LOGO TOP LEFT (620x305)
            // ---------------------------------------------------------
            Image logo = Image.getInstance("src/main/resources/static/logo.png");
            logo.scaleAbsolute(155, 76); // perfect scale (620x305 → 1/4 size)
            logo.setAbsolutePosition(40, document.getPageSize().getHeight() - 90);
            document.add(logo);

            document.add(new Paragraph("\n")); // spacing


            // ---------------------------------------------------------
            // TITLE
            // ---------------------------------------------------------
            Paragraph title = new Paragraph("Book Allotment Transcript", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(15);
            document.add(title);


            // ---------------------------------------------------------
            // STUDENT INFORMATION
            // ---------------------------------------------------------
            addSection(document, "Student Information");
            document.add(boxedLine("Name", sName));
            document.add(boxedLine("Code", sCode));
            document.add(boxedLine("Email", sEmail));
            document.add(boxedLine("Phone", sPhone));


            // ---------------------------------------------------------
            // BOOK INFORMATION
            // ---------------------------------------------------------
            addSection(document, "Book Information");
            document.add(boxedLine("Book Name", bName));
            document.add(boxedLine("Author", author));
            document.add(boxedLine("Publisher", publisher));
            document.add(boxedLine("Price", String.valueOf(price)));


            // ---------------------------------------------------------
            // ALLOTMENT INFORMATION
            // ---------------------------------------------------------
            addSection(document, "Allotment Information");
            document.add(boxedLine("Allotment ID", String.valueOf(id)));
            document.add(boxedLine("Unit", String.valueOf(unit)));
            document.add(boxedLine("Fare", String.valueOf(fare)));
            document.add(boxedLine("Issued Date", issueDate.toString()));
            document.add(boxedLine("Return Date", returnDate.toString()));
            document.add(boxedLine("Status", status));



            // ---------------------------------------------------------
            // SIGNATURE (BOTTOM RIGHT)
            // ---------------------------------------------------------
            PdfContentByte cb = writer.getDirectContent();
            ColumnText.showTextAligned(
                    cb,
                    Element.ALIGN_RIGHT,
                    new Phrase("InfoScript Library", footerFont),
                    document.right(), 40, 0
            );

            // ---------------------------------------------------------
            // FOOTER LINE + TIME (BOTTOM CENTER)
            // ---------------------------------------------------------
            PdfContentByte footerCb = writer.getDirectContent();

            // Draw footer line
            footerCb.setColorStroke(PRIMARY);
            footerCb.setLineWidth(1f);
            footerCb.moveTo(document.left(), 50);         // starting X, Y position
            footerCb.lineTo(document.right(), 50);        // ending X, Y position
            footerCb.stroke();

            // Add timestamp text
            String timestamp = "Generated on: " + new java.text.SimpleDateFormat("dd MMM yyyy, HH:mm a").format(new java.util.Date());

            ColumnText.showTextAligned(
                    footerCb,
                    Element.ALIGN_CENTER,
                    new Phrase(timestamp, new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.BLACK)),
                    (document.right() + document.left()) / 2,
                    35,
                    0
            );

            document.close();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("inline",
                    "allotment_transcript_" + allotId + ".pdf");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(baos.toByteArray());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }


}




