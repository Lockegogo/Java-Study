package com.locke.controller;

import com.locke.pojo.Books;
import com.locke.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    @Qualifier("BookServiceImpl")
    private BookService bookService;

    // 查询全部的书籍，并且返回到一个书籍展示页面
    @RequestMapping("/allBook")
    public String list(Model model) {
        List<Books> list = bookService.queryAllBook();
        model.addAttribute("list", list);
        return "allBook";
    }

    // 跳转到增加书籍页面
    @RequestMapping("/toAddBook")
    public String toAddPaper() {
        return "addBook";
    }

    // 添加书籍的请求
    @RequestMapping("/addBook")
    public String addPaper(Books books) {
        System.out.println(books);
        bookService.addBook(books);
        // 重定向
        return "redirect:/book/allBook";
    }

    // 跳转到修改页面
    @RequestMapping("/toUpdateBook/{bookId}")
    // @PathVariable(): RestFul 风格
    public String toUpdateBook(Model model, @PathVariable("bookId") int id) {
        Books books = bookService.queryBookById(id);
        System.out.println(books);
        model.addAttribute("book", books);
        return "updateBook";
    }

    // 修改书籍
    @RequestMapping("/updateBook")
    public String updateBook(Model model, Books book) {
        System.out.println(book);
        // 调业务层去修改
        bookService.updateBook(book);
        Books books = bookService.queryBookById(book.getBookID());
        model.addAttribute("books", books);
        return "redirect:/book/allBook";
    }

    // 删除书籍
    @RequestMapping("/del/{bookId}")
    public String deleteBook(@PathVariable("bookId") int id) {
        bookService.deleteBookById(id);
        return "redirect:/book/allBook";
    }

    // 查询
    @RequestMapping("/queryBook")
    public String queryBook(String queryBookName, Model model) {
        System.err.println("要查询的书籍:" + queryBookName);
        Books books = bookService.queryBookByName(queryBookName);
        List<Books> list = new ArrayList<Books>();
        list.add(books);
        // 如果查询为空，返回全部的书籍
        if (books == null) {
            list = bookService.queryAllBook();
            model.addAttribute("error", "所查询书籍不在数据库中，请重新输入!");
        }

        model.addAttribute("list", list);
        return "allBook";
    }
}