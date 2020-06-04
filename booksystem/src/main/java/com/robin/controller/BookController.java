package com.robin.controller;

import com.robin.model.Book;
import com.robin.service.BookService;
import com.robin.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping("/all")
    public Result findAll() {
        List<Book> books = bookService.findAll();
        if (books.size() > 0) {
            return Result.success().add("data", books);
        } else {
            return Result.fail();
        }
    }

    @RequestMapping("/query/{id}")
    public Result findBookById(@PathVariable("id") Integer id) {
        Book book = bookService.findBookById(id);
        if (book != null) {
            return Result.success().add("data", book);
        } else {
            return Result.fail();
        }
    }

    @RequestMapping("/add")
    public Result addBook(@RequestBody Book book) {
        Integer rst = bookService.addBook(book);
        if (rst > 0) {
            return Result.success();
        } else {
            return Result.fail();
        }
    }

    @RequestMapping("/update")
    public Result updateBook(@RequestBody Book book) {
        Integer rst = bookService.updateBook(book);
        if (rst > 0) {
            return Result.success();
        } else {
            return Result.fail();
        }
    }

    @RequestMapping("/delete/{id}")
    public Result deleteBook(@PathVariable("id") Integer id) {
        Integer rst = bookService.deleteBook(id);
        if (rst > 0) {
            return Result.success();
        } else {
            return Result.fail();
        }
    }

}
